package com.libroom.auth.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.libroom.core.ui.CircledLogo
import com.libroom.core.ui.EditText
import com.libroom.core.ui.FilledButton
import com.libroom.core.ui.ProgressDialog
import com.libroom.core.ui.ScreenPreview
import com.libroom.core.ui.SpacerVertical
import com.waseem.mvi.collectState

@Composable
internal fun LoginRoute(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state by viewModel.collectState()
    LoginScreen(
        state = state,
        onAction = viewModel::action
    )
}

@Composable
internal fun LoginScreen(
    state: LoginUiState,
    onAction: (LoginAction) -> Unit = { }
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SpacerVertical(100)
        CircledLogo()
        SpacerVertical(20)
        Text(
            text = stringResource(R.string.login_screen_title),
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.SemiBold
            ),
        )
        SpacerVertical(8)
        Text(
            text = stringResource(R.string.login_screen_subtitle),
            style = MaterialTheme.typography.labelMedium
        )
        SpacerVertical(30)
        LoginForm(onAction = onAction)
        SpacerVertical(20)
        if (state is LoginUiState.Error) {
            Text(
                text = state.message,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.error
                )
            )
        }
    }

    if (state is LoginUiState.Loading) {
        ProgressDialog()
    }
}

@Composable
private fun LoginForm(
    onAction: (LoginAction) -> Unit
) {
    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }

    EditText(
        text = email.value,
        hint = stringResource(R.string.hint_email),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        onValueChange = { newValue: String -> email.value = newValue }
    )
    SpacerVertical(16)
    EditText(
        text = password.value,
        hint = stringResource(R.string.password),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        onValueChange = { newValue: String -> password.value = newValue }
    )
    SpacerVertical(25)
    FilledButton(
        text = stringResource(R.string.login),
        onClick = {
            onAction(LoginAction.SignIn(email.value, password.value))
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview
@Composable
private fun PreviewLogin(
    @PreviewParameter(LoginPreviewParamProvider::class) state: LoginUiState
) {
    ScreenPreview {
        LoginScreen(state = state)
    }
}

private class LoginPreviewParamProvider : PreviewParameterProvider<LoginUiState> {
    override val values: Sequence<LoginUiState>
        get() = sequenceOf(LoginUiState.Default, LoginUiState.Loading)

}

