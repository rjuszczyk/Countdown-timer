package com.example.androiddevchallenge.ui.widgets

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun OutlinedButtonWithImage(
    @DrawableRes drawableResource: Int,
    contentDescription: String,
    text: String,
    color: Color,
    modifier: Modifier = Modifier
) = OutlinedButton(
    onClick = { },
    border = BorderStroke(1.dp, color),
    shape = RoundedCornerShape(50), //50% percent
    colors = ButtonDefaults.outlinedButtonColors(contentColor = color),
    modifier = modifier
        .height(54.dp)


) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter = painterResource(id = drawableResource),
            contentDescription = contentDescription,
        )
        Text(text = text)
    }

}