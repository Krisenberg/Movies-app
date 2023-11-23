package com.example.movies

object ContentManager {
    private val mapIDtoTitle = mapOf(
        0 to R.string.lotr_1_title,
        1 to R.string.lotr_2_title,
        2 to R.string.lotr_3_title,
        3 to R.string.oppenheimer_title,
        4 to R.string.cars_title,
        5 to R.string.shrek_2_title,
        6 to R.string.casino_royale_title,
        7 to R.string.star_wars_title,
        8 to R.string.gladiator_title
    )

    private val mapIDtoTitleAbbrev = mapOf(
        0 to R.string.lotr_1_abbrev,
        1 to R.string.lotr_2_abbrev,
        2 to R.string.lotr_3_abbrev,
        3 to R.string.oppenheimer_title,
        4 to R.string.cars_title,
        5 to R.string.shrek_2_title,
        6 to R.string.casino_royale_title,
        7 to R.string.star_wars_abbrev,
        8 to R.string.gladiator_title
    )

    private val mapIDtoMainImage = mapOf(
        0 to R.drawable.main_img_lotr_1,
        1 to R.drawable.main_img_lotr_2,
        2 to R.drawable.main_img_lotr_3,
        3 to R.drawable.main_img_oppenheimer,
        4 to R.drawable.main_img_cars,
        5 to R.drawable.main_img_shrek_2,
        6 to R.drawable.main_img_casino_royale,
        7 to R.drawable.main_img_star_wars,
        8 to R.drawable.main_img_gladiator
    )

    private val scenes_0 = listOf(
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1
    )
    private val scenes_1 = listOf(
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1
    )
    private val scenes_2 = listOf(
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1
    )
    private val scenes_3 = listOf(
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1
    )
    private val scenes_4 = listOf(
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1
    )
    private val scenes_5 = listOf(
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1
    )
    private val scenes_6 = listOf(
        R.drawable.casino_royale_scene_1,
        R.drawable.casino_royale_scene_2,
        R.drawable.casino_royale_scene_3,
        R.drawable.casino_royale_scene_4,
        R.drawable.casino_royale_scene_5,
        R.drawable.casino_royale_scene_6,
        R.drawable.casino_royale_scene_7,
        R.drawable.casino_royale_scene_8,
        R.drawable.casino_royale_scene_9
    )
    private val scenes_7 = listOf(
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1
    )
    private val scenes_8 = listOf(
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1,
        R.drawable.main_img_lotr_1
    )

    private val mapIDtoScenes = mapOf(
        0 to scenes_0,
        1 to scenes_1,
        2 to scenes_2,
        3 to scenes_3,
        4 to scenes_4,
        5 to scenes_5,
        6 to scenes_6,
        7 to scenes_7,
        8 to scenes_8
    )

    private val actors_0 = listOf(
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1"
    )
    private val actors_1 = listOf(
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1"
    )
    private val actors_2 = listOf(
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1"
    )
    private val actors_3 = listOf(
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1"
    )
    private val actors_4 = listOf(
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1"
    )
    private val actors_5 = listOf(
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1"
    )
    private val actors_6 = listOf(
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1"
    )
    private val actors_7 = listOf(
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1"
    )
    private val actors_8 = listOf(
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1",
        "Actor 1"
    )


    private val mapIDtoActors = mapOf(
        0 to actors_0,
        1 to actors_1,
        2 to actors_2,
        3 to actors_3,
        4 to actors_4,
        5 to actors_5,
        6 to actors_6,
        7 to actors_7,
        8 to actors_8
    )

    private val mapIDtoDescription = mapOf(
        0 to "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
                "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
                "Lorem ipsum Lorem ipsum",
        1 to "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
                "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
                "Lorem ipsum Lorem ipsum",
        2 to "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
                "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
                "Lorem ipsum Lorem ipsum",
        3 to "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
                "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
                "Lorem ipsum Lorem ipsum",
        4 to "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
                "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
                "Lorem ipsum Lorem ipsum",
        5 to "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
                "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
                "Lorem ipsum Lorem ipsum",
        6 to "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
                "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
                "Lorem ipsum Lorem ipsum",
        7 to "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
                "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
                "Lorem ipsum Lorem ipsum",
        8 to "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
                "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
                "Lorem ipsum Lorem ipsum"
    )

    private val details_0 = listOf(
        "Duration: 30h",
        "Directed by: me",
        "LoremmeroL",
    )
    private val details_1 = listOf(
        "Duration: 30h",
        "Directed by: me",
        "LoremmeroL",
    )
    private val details_2 = listOf(
        "Duration: 30h",
        "Directed by: me",
        "LoremmeroL",
    )
    private val details_3 = listOf(
        "Duration: 30h",
        "Directed by: me",
        "LoremmeroL",
    )
    private val details_4 = listOf(
        "Duration: 30h",
        "Directed by: me",
        "LoremmeroL",
    )
    private val details_5 = listOf(
        "Duration: 30h",
        "Directed by: me",
        "LoremmeroL",
    )
    private val details_6 = listOf(
        "Duration: 30h",
        "Directed by: me",
        "LoremmeroL",
    )
    private val details_7 = listOf(
        "Duration: 30h",
        "Directed by: me",
        "LoremmeroL",
    )
    private val details_8 = listOf(
        "Duration: 30h",
        "Directed by: me",
        "LoremmeroL",
    )

    private val mapIDtoDetails = mapOf(
        0 to details_0,
        1 to details_1,
        2 to details_2,
        3 to details_3,
        4 to details_4,
        5 to details_5,
        6 to details_6,
        7 to details_7,
        8 to details_8
    )

    fun getTitlesList(): List<Int> {
        return mapIDtoTitle.values.toList()
    }

    fun getMainImagesList(): List<Int> {
        return mapIDtoMainImage.values.toList()
    }

    fun getAbbrev(iD: Int): Int {
        return mapIDtoTitleAbbrev[iD]!!
    }

    fun getScenesList(iD: Int): List<Int> {
        return mapIDtoScenes[iD]!!
    }

    fun getActorsList(iD: Int): List<String> {
        return mapIDtoActors[iD]!!
    }

    fun getDescription(iD: Int): String {
        return mapIDtoDescription[iD]!!
    }

    fun getDetails(iD: Int): List<String> {
        return mapIDtoDetails[iD]!!
    }
}