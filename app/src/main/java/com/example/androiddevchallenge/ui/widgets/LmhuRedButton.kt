package com.example.androiddevchallenge.ui.theme

import android.graphics.BitmapFactory
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.widgets.NinePatchImage


@Composable
fun LmhuRedButton(textS: String, modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = modifier
            .height(52.dp)
    ){
        val (image, text, button) = createRefs()

        val interactionSource = remember { MutableInteractionSource() }
        val isPressed by interactionSource.collectIsPressedAsState()
        val alpha: Float by animateFloatAsState(if (isPressed) 0.3f else 1f)

        NinePatchImage(
            alpha = alpha,
            bitmap = BitmapFactory.decodeResource(LocalContext.current.resources, R.mipmap.bg_red_button),
            modifier = Modifier.constrainAs(image) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }
        )

        Box(
            modifier = Modifier.constrainAs(button) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
                height = Dimension.value(54.dp)
            }
                .clip(RoundedCornerShape(26.dp))
                .background(color = Color(0xE3, 0x38, 0x40, 0xFF))
                .clickable(
                    onClick = {},
                    indication = rememberRipple(
                        color = Color.White
                    ),
                    interactionSource = interactionSource,
                ),
        )
        ProvideTextStyle(
            value = MaterialTheme.typography.button
        ) {
            Text(text = textS,
                color = Color.White,
                modifier = Modifier.constrainAs(text) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.wrapContent
                    height = Dimension.wrapContent
                }.padding(24.dp, 0.dp, 24.dp, 2.dp).clickable(enabled = false, onClick = {})
            )
        }
    }
}