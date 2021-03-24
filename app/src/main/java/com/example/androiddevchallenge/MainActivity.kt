/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.LmhuRedButton
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.widgets.LmhuInputField
import com.example.androiddevchallenge.ui.widgets.LnhuWhiteButton
import com.example.androiddevchallenge.ui.widgets.OutlinedButtonWithImage
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            setDecorFitsSystemWindows(false)
            statusBarColor = 0x55000000
        }

//setContentView(R.layout.test)


        setContent {

            MyApp()
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    MyTheme {
        Surface(color = MaterialTheme.colors.background) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth().weight(1f)
                ){
                    Image(
                        painter = painterResource(id = R.mipmap.bg_login),
                        contentDescription = "Login",
                        modifier = Modifier.fillMaxHeight().align(Alignment.CenterEnd),
                        contentScale = ContentScale.FillHeight,
                    )

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.mipmap.lmhu_logo),
                            contentDescription = "lmhu logo",
                            modifier = Modifier.padding(0.dp, 7.dp, 0.dp, 0.dp)
                        )

                        LnhuWhiteButton(
                            textS = "New account",
                        )
                    }
                }


                var state by remember { mutableStateOf("Abc") }

                LmhuInputField(
                    value = state,
                    placeholder = "Username",
                    onValueChange = {
                        state = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 0.dp)
                )

                LmhuInputField(
                    value = state,
                    placeholder = "Password",
                    onValueChange = {
                        state = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 0.dp,16.dp, 8.dp)
                )


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = spacedBy(16.dp),
                    modifier = Modifier.padding(16.dp, 16.dp)
                ) {
                    LmhuRedButton("Sign in",
                        Modifier.weight(1f)
                    )

                    Button(
                        onClick = {  },
                        colors = buttonColors(
                            backgroundColor = Color.Transparent,
                            contentColor = Color(0xFF29007aL)
                        ),
                        elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp),
                        modifier = Modifier.weight(1f)) {
                        Text("Forgot password?")

                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = spacedBy(8.dp),
                    modifier = Modifier.padding(16.dp, 0.dp)
                ) {
                    Divider(Modifier.weight(1f))
                    Text(text="Or use your social account.", color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f))
                    Divider(Modifier.weight(1f))
                }

                Row(
                    horizontalArrangement = spacedBy(16.dp),
                    modifier = Modifier.padding(16.dp, 16.dp)
                ) {

                    OutlinedButtonWithImage(
                        drawableResource = R.mipmap.ic_fb,
                        contentDescription = "Facebook logo",
                        text = "Facebook",
                        color = Color(0xFF3b5998),
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedButtonWithImage(
                        drawableResource = R.mipmap.ic_google,
                        contentDescription = "Google logo",
                        text = "Google",
                        color = Color(0xFF868181),
                        modifier = Modifier.weight(1f)
                    )
                }


            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
