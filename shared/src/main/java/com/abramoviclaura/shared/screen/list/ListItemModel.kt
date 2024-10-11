package com.abramoviclaura.shared.screen.list

import com.abramoviclaura.shared.R

data class ListItemModel(
    val title: String,
    val subtitle: String,
    val categories: List<ListItemCategory>,
    val imageUrl: String
)

enum class ListItemCategory {
    CATEGORY_1,
    CATEGORY_2,
    CATEGORY_3,
    CATEGORY_4,
}

fun ListItemCategory.toStringRes() = when (this) {
    ListItemCategory.CATEGORY_1 -> R.string.list_item_category_1
    ListItemCategory.CATEGORY_2 -> R.string.list_item_category_2
    ListItemCategory.CATEGORY_3 -> R.string.list_item_category_3
    ListItemCategory.CATEGORY_4 -> R.string.list_item_category_4
}
