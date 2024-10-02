package com.abramoviclaura.shared.screen.bouncingball

import kotlin.random.Random

object BouncingBallValues {

    const val BALL_COUNT = 20

    fun getRandomColor() = (Math.random() * 16777215).toInt() or (0xFF shl 24)
    fun getRandomStartDelay() = Random.nextInt(10, 100).toLong()
    fun getRandomDuration() = Random.nextInt(1000, 6000).toLong()
}

