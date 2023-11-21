package com.example.movies

object ContentManager {
    private val mapIDtoTitle = mapOf(
        1 to R.string.lotr_1_title,
        2 to R.string.lotr_2_title,
        3 to R.string.lotr_3_title,
        4 to R.string.lotr_1_title,
        5 to R.string.lotr_2_title,
        6 to R.string.lotr_3_title,
        7 to R.string.lotr_1_title,
        8 to R.string.lotr_2_title,
        9 to R.string.lotr_3_title
    )

    private val mapIDtoMainImage = mapOf(
        1 to R.drawable.main_img_lotr_1,
        2 to R.drawable.main_img_lotr_2,
        3 to R.drawable.main_img_lotr_3,
        4 to R.drawable.main_img_oppenheimer,
        5 to R.drawable.main_img_cars,
        6 to R.drawable.main_img_shrek_2,
        7 to R.drawable.main_img_lotr_1,
        8 to R.drawable.main_img_lotr_2,
        9 to R.drawable.main_img_lotr_3
    )

    fun GetTitlesList(): List<Int> {
        return mapIDtoTitle.values.toList()
    }

    fun GetMainImagesList(): List<Int> {
        return mapIDtoMainImage.values.toList()
    }
}