package com.abramoviclaura.shared.screen.details

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum

object DetailsValues {

    private const val WORD_COUNT = 50

    fun getLoremIpsumText() = LoremIpsum(WORD_COUNT).values.joinToString { it }
}
