package com.abramoviclaura.composeui.screen.bouncingball

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInBounce
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.abramoviclaura.shared.screen.bouncingball.BouncingBallValues
import kotlin.random.Random
import com.abramoviclaura.shared.R as SharedR

@Composable
fun BouncingBallScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        repeat(BouncingBallValues.BALL_COUNT) {
            BouncingBall(modifier = Modifier.align(Alignment.BottomStart))
        }
    }
}

@Composable
private fun BouncingBall(modifier: Modifier = Modifier) {
    val windowHeight = LocalConfiguration.current.screenHeightDp
    val windowWidth = LocalConfiguration.current.screenWidthDp
    val size = dimensionResource(id = SharedR.dimen.bouncing_ball_size)

    val offsetX = remember { Random.nextInt(0, windowWidth - size.value.toInt()).toFloat() }
    val randomHeight = remember { Random.nextInt(windowHeight / 2, windowHeight - size.value.toInt()) }

    val offsetY = remember { Animatable(0f) }
    val color = remember { Color(BouncingBallValues.getRandomColor()) }
    val startDelay = remember { BouncingBallValues.getRandomStartDelay() }
    val duration = remember { BouncingBallValues.getRandomDuration() }

    LaunchedEffect(Unit) {
        offsetY.animateTo(
            targetValue = -randomHeight.dp.value,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = duration,
                    easing = EaseInBounce,
                    delayMillis = startDelay
                ),
                repeatMode = RepeatMode.Reverse
            )
        )
    }

    Icon(
        painter = painterResource(id = SharedR.drawable.ic_basketball),
        contentDescription = null,
        tint = color,
        modifier = modifier
            .offset(y = offsetY.value.dp, x = offsetX.dp)
            .size(size)
    )
}
