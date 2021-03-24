package com.example.androiddevchallenge.ui.widgets

import android.graphics.Bitmap
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.core.graphics.get
import kotlin.math.roundToInt

@Composable
fun NinePatchImage(alpha: Float, bitmap: Bitmap, modifier: Modifier) {
    Canvas(modifier = modifier,
        onDraw = {
            var topStart = -1
            var topEnd = -1
            var leftStart = -1
            var leftEnd = -1
            var rightStart = -1
            var rightEnd = -1
            var bottomStart = -1
            var bottomEnd = -1
            for (i in 0 until bitmap.width) {
                if (bitmap.get(i, 0) != 0) {
                    topStart = i
                    break
                }
            }
            for (i in topStart until bitmap.width) {
                if (bitmap.get(i, 0) == 0) {
                    topEnd = i
                    break
                }
            }
            for (i in 0 until bitmap.height) {
                if (bitmap.get(0, i) != 0) {
                    leftStart = i
                    break
                }
            }
            for (i in leftStart until bitmap.height) {
                if (bitmap.get(0, i) == 0) {
                    leftEnd = i
                    break
                }
            }
            for (i in 0 until bitmap.width) {
                if (bitmap.get(i, bitmap.height-1) != 0) {
                    bottomStart = i
                    break
                }
            }
            if(bottomStart == -1) {
                bottomStart = 0
                bottomEnd = bitmap.width-1
            } else {
                for (i in bottomStart until bitmap.width) {
                    if (bitmap.get(i, bitmap.height - 1) == 0) {
                        bottomEnd = i
                        break
                    }
                }
            }
            for (i in 0 until bitmap.height) {
                if (bitmap.get(bitmap.width-1, i) != 0) {
                    rightStart = i
                    break
                }
            }
            if(rightStart == -1) {
                rightStart = 0
                rightEnd = bitmap.width-1
            } else {
                for (i in rightStart until bitmap.height) {
                    if (bitmap.get(bitmap.width - 1, i) == 0) {
                        rightEnd = i
                        break
                    }
                }
            }

            val X = topStart-1
            val Y = leftStart-1
            val Xprime = bitmap.width-topEnd-1
            val Yprime = bitmap.height-leftEnd-1

            val x = bottomStart-1
            val y = rightStart-1
            val xprime = bitmap.width-bottomEnd-1
            val yprime = bitmap.height-rightEnd-1

            val imageBitmap = bitmap.asImageBitmap()
            //draw left top corner
            drawImage(
                image = imageBitmap,
                srcOffset = IntOffset(1,1),
                srcSize = IntSize(X,Y),
                dstOffset = IntOffset(-x,-y),
                dstSize = IntSize(X,Y),
                alpha = alpha,
            )
            //draw right top corner
            drawImage(
                image = imageBitmap,
                srcOffset = IntOffset(topEnd,1),
                srcSize = IntSize(Xprime,Y),
                dstOffset = IntOffset((size.width-(Xprime-xprime)).roundToInt(),-y),
                dstSize = IntSize(Xprime,Y),
                alpha = alpha,
            )
            //draw top side
            drawImage(
                image = imageBitmap,
                srcOffset = IntOffset(topStart,1),
                srcSize = IntSize(topEnd-topStart,Y),
                dstOffset = IntOffset(X-x,-y),
                dstSize = IntSize((size.width-(X-x)-(Xprime-xprime)).roundToInt(), Y),
                alpha = alpha,
            )

            //draw left side
            drawImage(
                image = imageBitmap,
                srcOffset = IntOffset(1,leftStart),
                srcSize = IntSize(X, leftEnd-leftStart),
                dstOffset = IntOffset(-x,Y-y),
                dstSize = IntSize(X, (size.height-(Y-y)-(Yprime-yprime)).roundToInt()),
                alpha = alpha,
            )

            //draw left bottom corner
            drawImage(
                image = imageBitmap,
                srcOffset = IntOffset(1,bitmap.height-Yprime-1),
                srcSize = IntSize(X,Yprime),
                dstOffset = IntOffset(-x, (size.height-(Yprime-yprime)).roundToInt()),
                dstSize = IntSize(X,Yprime),
                alpha = alpha,
            )

            //draw right bottom corner
            drawImage(
                image = imageBitmap,
                srcOffset = IntOffset(topEnd,bitmap.height-Yprime-1),
                srcSize = IntSize(Xprime,Yprime),
                dstOffset = IntOffset((size.width-(Xprime-xprime)).roundToInt(), (size.height-(Yprime-yprime)).roundToInt()),
                dstSize = IntSize(Xprime,Yprime),
                alpha = alpha,
            )

            //draw bottom side
            drawImage(
                image = imageBitmap,
                srcOffset = IntOffset(topStart,bitmap.height-Yprime-1),
                srcSize = IntSize(topEnd-topStart,Yprime),
                dstOffset = IntOffset(X-x, (size.height-(Yprime-yprime)).roundToInt()),
                dstSize = IntSize((size.width-(X-x)-(Xprime-xprime)).roundToInt(), Yprime),
                alpha = alpha,
            )

            //draw right side
            drawImage(
                image = imageBitmap,
                srcOffset = IntOffset(bitmap.width-Xprime-1, leftStart),
                srcSize = IntSize(Xprime, leftEnd-leftStart),
                dstOffset = IntOffset((size.width-(Xprime-xprime)).roundToInt(), Y-y),
                dstSize = IntSize(Xprime, (size.height-(Y-y)-(Yprime-yprime)).roundToInt()),
                alpha = alpha,
            )

            //draw center
            drawImage(
                image = imageBitmap,
                srcOffset = IntOffset(X,Y),
                srcSize = IntSize(bitmap.width-Xprime-X, bitmap.height-Yprime-Y),
                dstOffset = IntOffset(X-x, Y-y),
                dstSize = IntSize((size.width-(X-x)-(Xprime-xprime)).roundToInt(), (size.height-(Y-y)-(Yprime-yprime)).roundToInt()),
                alpha = alpha,
            )
        })
}