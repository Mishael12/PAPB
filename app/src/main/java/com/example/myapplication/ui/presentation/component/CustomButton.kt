package com.example.myapplication.ui.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ButtonType
import com.example.myapplication.ui.theme.Neutral51
import com.example.myapplication.ui.theme.Type

@Composable
fun CustomButton(
    onClick: () -> Unit,
    text: String,
    type: String,
    imageVector: ImageVector? = null,
    modifier: Modifier = Modifier
) {
    when (type) {
        ButtonType.LARGE -> ButtonLarge(onClick = { onClick() }, text = text, modifier)
        ButtonType.MEDIUM -> ButtonMedium(onClick = { onClick() }, text = text, modifier)
        ButtonType.SMALL -> imageVector?.let { ButtonMenu(onClick = { onClick() }, text = text, imageVector = it, modifier) }
        ButtonType.ORDER -> ButtonOrder(onClick = { onClick() }, text = text, modifier)
    }
}

@Composable
private fun ButtonLarge(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(containerColor = Neutral51),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .height(30.dp)
            .width(216.dp),
    ) {
        Text(
            text = text,
            style = Type.text2xsSemiBold(),
            color = Color.White
        )
    }
}

@Composable
private fun ButtonMedium(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(containerColor = Neutral51),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .height(48.dp)
            .sizeIn(minWidth = 155.dp)
    ) {
        Text(
            text = text,
            style = Type.text2xsMedium(),
            color = Color.White
        )
    }
}

@Composable
private fun ButtonMenu(
    onClick: () -> Unit,
    text: String,
    imageVector: ImageVector,
    modifier: Modifier = Modifier
){
    Button(
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(containerColor = Neutral51),
        shape = RoundedCornerShape(100.dp),
        modifier = modifier
            .height(45.dp)
            .width(300.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = text,
                style = Type.text2xsSemiBold(),
                color = Color.White,
                fontSize = 17.sp
            )
            Spacer(modifier = Modifier.width(130.dp))
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Composable
private fun ButtonOrder(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier
){
    Button(
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(containerColor = Neutral51),
        shape = RoundedCornerShape(100.dp),
        modifier = modifier
            .height(45.dp)
            .width(300.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = text,
                style = Type.text2xsSemiBold(),
                color = Color.White,
                fontSize = 17.sp
            )
        }
    }
}