package com.libroom.core.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.libroom.core.ui.theme.LibroomTheme

@Composable
fun ThemedPreview(
    content: @Composable () -> Unit
) {
    LibroomTheme(
        content = content
    )
}

@Composable
fun ScreenPreview(
    content: @Composable () -> Unit
) {
    LibroomTheme {
        Scaffold {
            Box(Modifier.fillMaxSize().padding(it)) {
                content()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetPreview(
    sheetDragHandle: @Composable () -> Unit = {},
    content: @Composable () -> Unit
) {
    LibroomTheme {
        val bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Expanded
        )
        val scaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = bottomSheetState
        )

        BottomSheetScaffold(
            sheetContent = { content() },
            sheetContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            scaffoldState = scaffoldState,
            sheetDragHandle = sheetDragHandle
        ) {

        }
    }
}