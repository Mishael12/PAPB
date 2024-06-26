package com.example.myapplication.ui.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.Neutral900
import com.example.myapplication.ui.theme.Primary100
import com.example.myapplication.ui.theme.Primary400
import com.example.myapplication.ui.theme.Primary800
import com.example.myapplication.ui.theme.Primary900

@Composable
fun CustomTextField(
    text: String,
    placeholder: String,
    trailingIcon: ImageVector? = null,
    showPassword: Boolean = false,
    onValueChange: (String) -> Unit = {},
    onPasswordToggle: (Boolean) -> Unit = {},
    label: String? = null,
    isPassword: Boolean = false,
    isNumeric: Boolean = false,
    maxLine: Int = 1,
    minLine: Int = 1,
    onIconClick: () -> Unit = {},
    modifier: Modifier = Modifier
        .fillMaxWidth()
){
    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = {
            onValueChange(it)
        },
        placeholder = {
            Text(text = placeholder)
        },
        trailingIcon = {
            if (trailingIcon != null) {
                if (isPassword) {
                    if (!showPassword) {
                        IconButton(onClick = { onPasswordToggle(!showPassword) }) {
                            Icon(
                                imageVector = Icons.Default.VisibilityOff,
                                contentDescription = "show",
                                Modifier
                                    .size(25.dp)
                                    .clickable { onPasswordToggle(!showPassword) },
                                tint = Color.Black
                            )
                        }
                    } else {
                        IconButton(onClick = { onPasswordToggle(!showPassword) }) {
                            Icon(
                                imageVector = Icons.Default.Visibility,
                                contentDescription = "",
                                Modifier
                                    .size(25.dp)
                                    .clickable { onPasswordToggle(!showPassword) },
                                tint = Color.Black
                            )
                        }
                    }
                } else {
                    IconButton(onClick = { onIconClick() }) {
                        Icon(
                            imageVector = trailingIcon,
                            contentDescription = "",
                            Modifier
                                .size(25.dp)
                                .clickable { onPasswordToggle(!showPassword) },
                            tint = Color.Black
                        )
                    }
                }
            }
        },
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            unfocusedIndicatorColor = Neutral900,
            focusedIndicatorColor = Color.Black,
            cursorColor = Primary900,
            focusedTextColor = Color.Black,
            unfocusedPlaceholderColor = Color.Black,
            focusedLabelColor = Primary800,
            unfocusedLabelColor = Primary800
        ),
        label = {
            label?.let {
                Text(text = it)
            }
        },
        visualTransformation =
        if (isPassword) {
            if (showPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        } else {
            VisualTransformation.None
        },
        keyboardOptions =
        if (isPassword) {
            KeyboardOptions(keyboardType = KeyboardType.Password)
        } else if (isNumeric) {
            KeyboardOptions(keyboardType = KeyboardType.Number)
        } else {
            KeyboardOptions(keyboardType = KeyboardType.Text)
        },
        maxLines = maxLine,
        minLines = minLine
    )
}