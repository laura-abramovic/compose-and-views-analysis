package com.abramoviclaura.composeui.screen.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.abramoviclaura.composeui.screen.list.components.ListItemCard
import com.abramoviclaura.composeui.ui.theme.ColorBackground
import com.abramoviclaura.composeui.ui.theme.Typography
import com.abramoviclaura.shared.screen.list.ListDataProvider
import com.abramoviclaura.shared.R as SharedR

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListScreen() {
    val items = ListDataProvider.listItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBackground)
            .systemBarsPadding(),
    ) {
        Text(
            text = stringResource(SharedR.string.item_list_screen_title),
            style = Typography.headlineLarge,
            modifier = Modifier.padding(dimensionResource(SharedR.dimen.common_spacing_l))
        )

        CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(dimensionResource(SharedR.dimen.common_spacing_m))
            ) {
                itemsIndexed(
                    items = items,
                    key = { _, item -> item.id }
                ) { index, item ->
                    ListItemCard(item)

                    if (index != items.lastIndex) {
                        Spacer(modifier = Modifier.height(dimensionResource(SharedR.dimen.common_spacing_m)))
                    }
                }
            }
        }
    }
}
