package com.libroom.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun EditText(
    modifier: Modifier = Modifier,
    text: String,
    hint: String = "",
    singleLine: Boolean = true,
    shape: Shape = MaterialTheme.shapes.small,
    colors: TextFieldColors = TextFieldDefaults.colors(
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant
    ),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = text,
        placeholder = { Text(text = hint) },
        onValueChange = onValueChange,
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        shape = shape,
        colors = colors,
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun SearchBox(
    modifier: Modifier = Modifier
) {
    val text = rememberSaveable { mutableStateOf("") }
    TextField(
        value = text.value,
//        placeholder = { Text(text = stringResource(id = R.string.search_box_placeholder)) },
        onValueChange = { newValue: String -> text.value = newValue },
        singleLine = true,
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = modifier.fillMaxWidth(),
        leadingIcon = {  }
    )
}

@Preview
@Composable
private fun PreviewSearchBox() {
    ThemedPreview {
        Column {
//            SearchBox(
//                modifier = Modifier.padding(dimensionResource(id = R.dimen.onboarding_padding))
//            )
            EditText(
                modifier = Modifier.padding(20.dp),
                hint = "Email",
                text = "",
                onValueChange = {}
            )
        }
    }
}