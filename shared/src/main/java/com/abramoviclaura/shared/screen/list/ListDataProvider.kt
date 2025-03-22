package com.abramoviclaura.shared.screen.list

import kotlin.random.Random

object ListDataProvider {
    private const val ITEM_COUNT = 100

    private val imageUrls = listOf(
        "https://cdn.pixabay.com/photo/2021/02/23/10/19/bird-6043004_1280.jpg", // bird
        "https://cdn.pixabay.com/photo/2023/08/18/01/32/cat-8197577_1280.jpg", // cat
        "https://cdn.pixabay.com/photo/2020/03/31/19/20/dog-4988985_1280.jpg", // dog
        "https://cdn.pixabay.com/photo/2018/09/02/15/34/owl-3649048_1280.jpg", // owl
        "https://cdn.pixabay.com/photo/2018/04/26/14/50/weasel-3352109_1280.jpg", // weasel
        "https://cdn.pixabay.com/photo/2023/05/23/07/05/royal-gramma-basslet-8012082_1280.jpg", // fish
        "https://cdn.pixabay.com/photo/2022/05/16/18/17/sheep-7200918_1280.jpg", // sheep
        "https://cdn.pixabay.com/photo/2018/04/10/16/59/bear-3308068_1280.jpg", // polar bear
        "https://cdn.pixabay.com/photo/2017/03/07/19/10/bouquet-2124991_1280.jpg", // ostrich
    )

    private val list = List(ITEM_COUNT) {
        val itemNum = it + 1

        ListItemModel(
            id = it,
            title = "List Item $itemNum",
            subtitle = "Item content for item $itemNum",
            categories = ListItemCategory.entries.filter { Random.nextBoolean() }, // Random subset
            imageUrl = imageUrls.random(),
            bookmarked = Random.nextBoolean()
        )
    }

    fun listItems(): List<ListItemModel> = list

    fun getItem(id: Int) = list[id]
}

