package com.libroom.core.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SpacerVertical(value: Int) = Spacer(Modifier.height(value.dp))

@Composable
fun SpacerHorizontal(value: Int) = Spacer(Modifier.width(value.dp))