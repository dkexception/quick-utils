package com.dkexception.quickutils.ui.composables

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun QuickUtilsEditText(
    modifier: Modifier,
    optionalInitialValue: String = "",
    optionalKeyboardType: KeyboardType = KeyboardType.Text,
    optionalHint: String = "",
    onChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = optionalInitialValue,
        onValueChange = {
            onChange(it)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = optionalKeyboardType
        ),
        placeholder = { Text(optionalHint) }
    )
}
