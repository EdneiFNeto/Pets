package com.pets.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldComponent(
    modifier: Modifier,
    text: MutableState<TextFieldValue>,
    label: String,
    isError: Boolean,
    keyboardOptions: KeyboardOptions,
    onValueChange: (TextFieldValue) -> Unit
) {
    TextField(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        value = text.value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        placeholder = { Text(text = label) },
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.colors(
            disabledPlaceholderColor = Color.Black,
            disabledTextColor = Color.Black,
            disabledLabelColor = Color.Black,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            errorTextColor = Color.Black,
            focusedTextColor = Color.Black,
            errorCursorColor = Color.Black,
            errorLabelColor = Color.Black,
            errorPlaceholderColor = Color.Black,
            focusedLabelColor = Color.Black,
            focusedPlaceholderColor = Color.Black,
            unfocusedPlaceholderColor = Color.Black,
            unfocusedLabelColor = Color.Black,
            unfocusedTextColor = Color.Black,
            errorSupportingTextColor = Color.Black
        ),
        trailingIcon = {
            if (isError)
                Icon(Icons.Filled.Info, "error", tint = Color.Red)
        },
        isError = isError,
    )
}
