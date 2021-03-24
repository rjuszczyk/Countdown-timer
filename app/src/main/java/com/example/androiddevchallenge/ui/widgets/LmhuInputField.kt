package com.example.androiddevchallenge.ui.widgets

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun LmhuInputField(
    value: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current.copy(

    ),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    var textFieldValueState by remember { mutableStateOf(TextFieldValue(text = value)) }
    val textFieldValue = textFieldValueState.copy(text = value)


    val mutableInteractionSource = remember { MutableInteractionSource() }
    val isFocusedState by mutableInteractionSource.collectIsFocusedAsState()

    val color by animateColorAsState(
        if (isFocusedState) MaterialTheme.colors.primary else Color(
            0xFF666666
        )
    )

    ConstraintLayout(modifier = modifier) {
        val (text, placeholderC, icon, divider) = createRefs()


        Icon(
            painter = painterResource(id = R.drawable.ic_login_profile),
            contentDescription = placeholder,
            tint = color,
            modifier = Modifier.constrainAs(icon) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            }
            .padding(20.dp,0.dp,0.dp,0.dp)
        )

        BasicTextField(
            value = textFieldValue,
            onValueChange = {
                textFieldValueState = it
                if (value != it.text) {
                    onValueChange(it.text)
                }
            },
            interactionSource = mutableInteractionSource,
            cursorBrush = SolidColor(MaterialTheme.colors.primary),
            textStyle = textStyle,
            enabled = enabled,
            readOnly = readOnly,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLines,
            visualTransformation = visualTransformation,
            modifier = Modifier
                .constrainAs(text) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(icon.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                }
                .padding(15.dp,14.dp,20.dp,15.dp)
        )

//        if(value.isEmpty()) {
            Text(
                text = placeholder,
                fontSize = textStyle.fontSize,
                fontFamily = textStyle.fontFamily,
                letterSpacing = textStyle.letterSpacing,
                fontWeight = textStyle.fontWeight,
                color = textStyle.color,
                textAlign = textStyle.textAlign,
                modifier = Modifier
                    .constrainAs(placeholderC) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(icon.end)
                        width = Dimension.wrapContent
                        height = Dimension.wrapContent
                    }
                    .alpha(if(value.isEmpty()) 0.5f else 0.0f)
                    .padding(15.dp,14.dp,0.dp,15.dp)
            )
//        }

        Divider(color = color, thickness = 1.dp, modifier = Modifier.constrainAs(divider) {
//            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
            height = Dimension.wrapContent
        })
    }


//        TextField(
//            value = value,
//            onValueChange = onValueChange,
//            interactionSource = mutableInteractionSource,
//            placeholder = {
//                Text(text = "Username", modifier=Modifier.alpha(0.5f))
//            },
//            leadingIcon = {
//                Icon(painter = painterResource(id = R.drawable.ic_login_profile), "")
//            },
//            colors = TextFieldDefaults.textFieldColors(
//                backgroundColor = Color.Transparent,
//                leadingIconColor = color
////                placeholderColor = Color.Green
//            ),
//            modifier = modifier,
//        )







}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        Surface {
            Box {
                LmhuInputField(
                    value = "123456789123456789",
                    placeholder = "test tetestset",
                    onValueChange = {},
                )
            }
        }

    }
}