package com.example.movies


object ContentManager {
//    private val mapIDtoTitle = mapOf(
//        0 to R.string.lotr_1_title,
//        1 to R.string.lotr_2_title,
//        2 to R.string.lotr_3_title,
//        3 to R.string.oppenheimer_title,
//        4 to R.string.cars_title,
//        5 to R.string.shrek_2_title,
//        6 to R.string.casino_royale_title,
//        7 to R.string.star_wars_title,
//        8 to R.string.gladiator_title
//    )
//
//    private val mapIDtoTitleAbbrev = mapOf(
//        0 to R.string.lotr_1_abbrev,
//        1 to R.string.lotr_2_abbrev,
//        2 to R.string.lotr_3_abbrev,
//        3 to R.string.oppenheimer_title,
//        4 to R.string.cars_title,
//        5 to R.string.shrek_2_title,
//        6 to R.string.casino_royale_title,
//        7 to R.string.star_wars_abbrev,
//        8 to R.string.gladiator_title
//    )
//
//    private val mapIDtoMainImage = mapOf(
//        0 to R.drawable.main_img_lotr_1,
//        1 to R.drawable.main_img_lotr_2,
//        2 to R.drawable.main_img_lotr_3,
//        3 to R.drawable.main_img_oppenheimer,
//        4 to R.drawable.main_img_cars,
//        5 to R.drawable.main_img_shrek_2,
//        6 to R.drawable.main_img_casino_royale,
//        7 to R.drawable.main_img_star_wars,
//        8 to R.drawable.main_img_gladiator
//    )

//    private val scenes_0 = listOf(
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1
//    )
//    private val scenes_1 = listOf(
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1
//    )
//    private val scenes_2 = listOf(
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1
//    )
//    private val scenes_3 = listOf(
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1
//    )
//    private val scenes_4 = listOf(
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1
//    )
//    private val scenes_5 = listOf(
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1
//    )
//    private val scenes_6 = listOf(
//        R.drawable.casino_royale_scene_1,
//        R.drawable.casino_royale_scene_2,
//        R.drawable.casino_royale_scene_3,
//        R.drawable.casino_royale_scene_4,
//        R.drawable.casino_royale_scene_5,
//        R.drawable.casino_royale_scene_6,
//        R.drawable.casino_royale_scene_7,
//        R.drawable.casino_royale_scene_8,
//        R.drawable.casino_royale_scene_9
//    )
//    private val scenes_7 = listOf(
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1
//    )
//    private val scenes_8 = listOf(
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1,
//        R.drawable.main_img_lotr_1
//    )

//    private val mapIDtoScenes = mapOf(
//        0 to scenes_0,
//        1 to scenes_1,
//        2 to scenes_2,
//        3 to scenes_3,
//        4 to scenes_4,
//        5 to scenes_5,
//        6 to scenes_6,
//        7 to scenes_7,
//        8 to scenes_8
//    )

//    private val actors_0 = listOf(
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1"
//    )
//    private val actors_1 = listOf(
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1"
//    )
//    private val actors_2 = listOf(
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1"
//    )
//    private val actors_3 = listOf(
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1"
//    )
//    private val actors_4 = listOf(
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1"
//    )
//    private val actors_5 = listOf(
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1"
//    )
//    private val actors_6 = listOf(
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1"
//    )
//    private val actors_7 = listOf(
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1"
//    )
//    private val actors_8 = listOf(
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1",
//        "Actor 1"
//    )


//    private val mapIDtoActors = mapOf(
//        0 to actors_0,
//        1 to actors_1,
//        2 to actors_2,
//        3 to actors_3,
//        4 to actors_4,
//        5 to actors_5,
//        6 to actors_6,
//        7 to actors_7,
//        8 to actors_8
//    )
//
//    private val mapIDtoDescription = mapOf(
//        0 to "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
//                "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
//                "Lorem ipsum Lorem ipsum",
//        1 to "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
//                "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
//                "Lorem ipsum Lorem ipsum",
//        2 to "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
//                "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
//                "Lorem ipsum Lorem ipsum",
//        3 to "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
//                "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
//                "Lorem ipsum Lorem ipsum",
//        4 to "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
//                "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
//                "Lorem ipsum Lorem ipsum",
//        5 to "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
//                "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
//                "Lorem ipsum Lorem ipsum",
//        6 to "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
//                "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
//                "Lorem ipsum Lorem ipsum",
//        7 to "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
//                "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
//                "Lorem ipsum Lorem ipsum",
//        8 to "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
//                "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum" +
//                "Lorem ipsum Lorem ipsum"
//    )

//    private val details_0 = listOf(
//        "Duration: 30h",
//        "Directed by: me",
//        "LoremmeroL",
//    )
//    private val details_1 = listOf(
//        "Duration: 30h",
//        "Directed by: me",
//        "LoremmeroL",
//    )
//    private val details_2 = listOf(
//        "Duration: 30h",
//        "Directed by: me",
//        "LoremmeroL",
//    )
//    private val details_3 = listOf(
//        "Duration: 30h",
//        "Directed by: me",
//        "LoremmeroL",
//    )
//    private val details_4 = listOf(
//        "Duration: 30h",
//        "Directed by: me",
//        "LoremmeroL",
//    )
//    private val details_5 = listOf(
//        "Duration: 30h",
//        "Directed by: me",
//        "LoremmeroL",
//    )
//    private val details_6 = listOf(
//        "Duration: 30h",
//        "Directed by: me",
//        "LoremmeroL",
//    )
//    private val details_7 = listOf(
//        "Duration: 30h",
//        "Directed by: me",
//        "LoremmeroL",
//    )
//    private val details_8 = listOf(
//        "Duration: 30h",
//        "Directed by: me",
//        "LoremmeroL",
//    )

//    private val mapIDtoDetails = mapOf(
//        0 to details_0,
//        1 to details_1,
//        2 to details_2,
//        3 to details_3,
//        4 to details_4,
//        5 to details_5,
//        6 to details_6,
//        7 to details_7,
//        8 to details_8
//    )

    private val lotr_1 = MovieDetails(
        mainImage = R.drawable.main_img_lotr_1,
        title = R.string.lotr_1_title,
        titleAbbrev = R.string.lotr_1_abbrev,
        description = R.string.lotr_1_description,
        details = listOf(R.string.lotr_1_detil_1, R.string.lotr_1_detil_2, R.string.lotr_1_detil_3),
        scenes = listOf(
            R.drawable.lotr_1_scene_1,
            R.drawable.lotr_1_scene_2,
            R.drawable.lotr_1_scene_3,
            R.drawable.lotr_1_scene_4,
            R.drawable.lotr_1_scene_5,
            R.drawable.lotr_1_scene_6,
            R.drawable.lotr_1_scene_7,
            R.drawable.lotr_1_scene_8,
            R.drawable.lotr_1_scene_9
        ),
        actors = listOf(
            R.string.lotr_1_actor_1,
            R.string.lotr_1_actor_2,
            R.string.lotr_1_actor_3,
            R.string.lotr_1_actor_4,
            R.string.lotr_1_actor_5,
            R.string.lotr_1_actor_6,
            R.string.lotr_1_actor_7,
            R.string.lotr_1_actor_8,
            R.string.lotr_1_actor_9,
            R.string.lotr_1_actor_10,
            R.string.lotr_1_actor_11,
            R.string.lotr_1_actor_12,
        )
    )

    private val lotr_2 = MovieDetails(
        mainImage = R.drawable.main_img_lotr_2,
        title = R.string.lotr_2_title,
        titleAbbrev = R.string.lotr_2_abbrev,
        description = R.string.lotr_2_description,
        details = listOf(R.string.lotr_2_detil_1, R.string.lotr_2_detil_2, R.string.lotr_2_detil_3),
        scenes = listOf(
            R.drawable.lotr_2_scene_1,
            R.drawable.lotr_2_scene_2,
            R.drawable.lotr_2_scene_3,
            R.drawable.lotr_2_scene_4,
            R.drawable.lotr_2_scene_5,
            R.drawable.lotr_2_scene_6,
            R.drawable.lotr_2_scene_7,
            R.drawable.lotr_2_scene_8,
            R.drawable.lotr_2_scene_9
        ),
        actors = listOf(
            R.string.lotr_2_actor_1,
            R.string.lotr_2_actor_2,
            R.string.lotr_2_actor_3,
            R.string.lotr_2_actor_4,
            R.string.lotr_2_actor_5,
            R.string.lotr_2_actor_6,
            R.string.lotr_2_actor_7,
            R.string.lotr_2_actor_8,
            R.string.lotr_2_actor_9,
            R.string.lotr_2_actor_10,
            R.string.lotr_2_actor_11,
            R.string.lotr_2_actor_12,
        )
    )

    private val lotr_3 = MovieDetails(
        mainImage = R.drawable.main_img_lotr_3,
        title = R.string.lotr_3_title,
        titleAbbrev = R.string.lotr_3_abbrev,
        description = R.string.lotr_3_description,
        details = listOf(R.string.lotr_3_detil_1, R.string.lotr_3_detil_2, R.string.lotr_3_detil_3),
        scenes = listOf(
            R.drawable.lotr_3_scene_1,
            R.drawable.lotr_3_scene_2,
            R.drawable.lotr_3_scene_3,
            R.drawable.lotr_3_scene_4,
            R.drawable.lotr_3_scene_5,
            R.drawable.lotr_3_scene_6,
            R.drawable.lotr_3_scene_7,
            R.drawable.lotr_3_scene_8,
            R.drawable.lotr_3_scene_9
        ),
        actors = listOf(
            R.string.lotr_3_actor_1,
            R.string.lotr_3_actor_2,
            R.string.lotr_3_actor_3,
            R.string.lotr_3_actor_4,
            R.string.lotr_3_actor_5,
            R.string.lotr_3_actor_6,
            R.string.lotr_3_actor_7,
            R.string.lotr_3_actor_8,
            R.string.lotr_3_actor_9,
            R.string.lotr_3_actor_10,
            R.string.lotr_3_actor_11,
            R.string.lotr_3_actor_12,
        )
    )

    private val oppenheimer = MovieDetails(
        mainImage = R.drawable.main_img_oppenheimer,
        title = R.string.oppenheimer_title,
        titleAbbrev = R.string.oppenheimer_title,
        description = R.string.oppenheimer_description,
        details = listOf(R.string.oppenheimer_detil_1, R.string.oppenheimer_detil_2, R.string.oppenheimer_detil_3),
        scenes = listOf(
            R.drawable.oppenheimer_scene_1,
            R.drawable.oppenheimer_scene_2,
            R.drawable.oppenheimer_scene_3,
            R.drawable.oppenheimer_scene_4,
            R.drawable.oppenheimer_scene_5,
            R.drawable.oppenheimer_scene_6,
            R.drawable.oppenheimer_scene_7,
            R.drawable.oppenheimer_scene_8,
            R.drawable.oppenheimer_scene_9
        ),
        actors = listOf(
            R.string.oppenheimer_actor_1,
            R.string.oppenheimer_actor_2,
            R.string.oppenheimer_actor_3,
            R.string.oppenheimer_actor_4,
            R.string.oppenheimer_actor_5,
            R.string.oppenheimer_actor_6,
            R.string.oppenheimer_actor_7,
            R.string.oppenheimer_actor_8,
            R.string.oppenheimer_actor_9,
            R.string.oppenheimer_actor_10,
            R.string.oppenheimer_actor_11,
            R.string.oppenheimer_actor_12,
        )
    )

    private val cars = MovieDetails(
        mainImage = R.drawable.main_img_cars,
        title = R.string.cars_title,
        titleAbbrev = R.string.cars_title,
        description = R.string.cars_description,
        details = listOf(R.string.cars_detil_1, R.string.cars_detil_2, R.string.cars_detil_3),
        scenes = listOf(
            R.drawable.cars_scene_1,
            R.drawable.cars_scene_2,
            R.drawable.cars_scene_3,
            R.drawable.cars_scene_4,
            R.drawable.cars_scene_5,
            R.drawable.cars_scene_6,
            R.drawable.cars_scene_7,
            R.drawable.cars_scene_8,
            R.drawable.cars_scene_9
        ),
        actors = listOf(
            R.string.cars_actor_1,
            R.string.cars_actor_2,
            R.string.cars_actor_3,
            R.string.cars_actor_4,
            R.string.cars_actor_5,
            R.string.cars_actor_6,
            R.string.cars_actor_7,
            R.string.cars_actor_8,
            R.string.cars_actor_9,
            R.string.cars_actor_10,
        )
    )

    private val shrek_2 = MovieDetails(
        mainImage = R.drawable.main_img_shrek_2,
        title = R.string.shrek_2_title,
        titleAbbrev = R.string.shrek_2_title,
        description = R.string.shrek_2_description,
        details = listOf(R.string.shrek_2_detil_1, R.string.shrek_2_detil_2, R.string.shrek_2_detil_3),
        scenes = listOf(
            R.drawable.shrek_2_scene_1,
            R.drawable.shrek_2_scene_2,
            R.drawable.shrek_2_scene_3,
            R.drawable.shrek_2_scene_4,
            R.drawable.shrek_2_scene_5,
            R.drawable.shrek_2_scene_6,
            R.drawable.shrek_2_scene_7,
            R.drawable.shrek_2_scene_8,
            R.drawable.shrek_2_scene_9
        ),
        actors = listOf(
            R.string.shrek_2_actor_1,
            R.string.shrek_2_actor_2,
            R.string.shrek_2_actor_3,
            R.string.shrek_2_actor_4,
            R.string.shrek_2_actor_5,
            R.string.shrek_2_actor_6,
            R.string.shrek_2_actor_7,
            R.string.shrek_2_actor_8,
            R.string.shrek_2_actor_9,
            R.string.shrek_2_actor_10,
            R.string.shrek_2_actor_11,
            R.string.shrek_2_actor_12,
        )
    )

    private val casino_royale = MovieDetails(
        mainImage = R.drawable.main_img_casino_royale,
        title = R.string.casino_royale_title,
        titleAbbrev = R.string.casino_royale_title,
        description = R.string.casino_royale_description,
        details = listOf(R.string.casino_royale_detil_1, R.string.casino_royale_detil_2, R.string.casino_royale_detil_3),
        scenes = listOf(
            R.drawable.casino_royale_scene_1,
            R.drawable.casino_royale_scene_2,
            R.drawable.casino_royale_scene_3,
            R.drawable.casino_royale_scene_4,
            R.drawable.casino_royale_scene_5,
            R.drawable.casino_royale_scene_6,
            R.drawable.casino_royale_scene_7,
            R.drawable.casino_royale_scene_8,
            R.drawable.casino_royale_scene_9
        ),
        actors = listOf(
            R.string.casino_royale_actor_1,
            R.string.casino_royale_actor_2,
            R.string.casino_royale_actor_3,
            R.string.casino_royale_actor_4,
            R.string.casino_royale_actor_5,
            R.string.casino_royale_actor_6,
            R.string.casino_royale_actor_7,
            R.string.casino_royale_actor_8,
            R.string.casino_royale_actor_9,
            R.string.casino_royale_actor_10
        )
    )

    private val star_wars = MovieDetails(
        mainImage = R.drawable.main_img_star_wars,
        title = R.string.star_wars_title,
        titleAbbrev = R.string.star_wars_abbrev,
        description = R.string.star_wars_description,
        details = listOf(R.string.star_wars_detil_1, R.string.star_wars_detil_2, R.string.star_wars_detil_3),
        scenes = listOf(
            R.drawable.star_wars_scene_1,
            R.drawable.star_wars_scene_2,
            R.drawable.star_wars_scene_3,
            R.drawable.star_wars_scene_4,
            R.drawable.star_wars_scene_5,
            R.drawable.star_wars_scene_6,
            R.drawable.star_wars_scene_7,
            R.drawable.star_wars_scene_8,
            R.drawable.star_wars_scene_9
        ),
        actors = listOf(
            R.string.star_wars_actor_1,
            R.string.star_wars_actor_2,
            R.string.star_wars_actor_3,
            R.string.star_wars_actor_4,
            R.string.star_wars_actor_5,
            R.string.star_wars_actor_6,
            R.string.star_wars_actor_7,
            R.string.star_wars_actor_8,
            R.string.star_wars_actor_9,
            R.string.star_wars_actor_10
        )
    )

    private val gladiator = MovieDetails(
        mainImage = R.drawable.main_img_gladiator,
        title = R.string.gladiator_title,
        titleAbbrev = R.string.gladiator_title,
        description = R.string.gladiator_description,
        details = listOf(R.string.gladiator_detil_1, R.string.gladiator_detil_2, R.string.gladiator_detil_3),
        scenes = listOf(
            R.drawable.gladiator_scene_1,
            R.drawable.gladiator_scene_2,
            R.drawable.gladiator_scene_3,
            R.drawable.gladiator_scene_4,
            R.drawable.gladiator_scene_5,
            R.drawable.gladiator_scene_6,
            R.drawable.gladiator_scene_7,
            R.drawable.gladiator_scene_8,
            R.drawable.gladiator_scene_9
        ),
        actors = listOf(
            R.string.gladiator_actor_1,
            R.string.gladiator_actor_2,
            R.string.gladiator_actor_3,
            R.string.gladiator_actor_4,
            R.string.gladiator_actor_5,
            R.string.gladiator_actor_6,
            R.string.gladiator_actor_7,
            R.string.gladiator_actor_8,
            R.string.gladiator_actor_9,
            R.string.gladiator_actor_10,
            R.string.gladiator_actor_11,
            R.string.gladiator_actor_12
        )
    )

    private val movieDatabase = listOf(
        lotr_1,
        lotr_2,
        lotr_3,
        oppenheimer,
        cars,
        shrek_2,
        casino_royale,
        star_wars,
        gladiator
    )

    fun getDatabaseData(): List<MovieDetails>{
        return movieDatabase
    }

//    fun getTitlesList(): List<Int> {
//        return mapIDtoTitle.values.toList()
//    }
//
//    fun getMainImagesList(): List<Int> {
//        return mapIDtoMainImage.values.toList()
//    }
//
//    fun getAbbrev(iD: Int): Int {
//        return mapIDtoTitleAbbrev[iD]!!
//    }
//
//    fun getScenesList(iD: Int): List<Int> {
//        return mapIDtoScenes[iD]!!
//    }
//
//    fun getActorsList(iD: Int): List<String> {
//        return mapIDtoActors[iD]!!
//    }
//
//    fun getDescription(iD: Int): String {
//        return mapIDtoDescription[iD]!!
//    }
//
//    fun getDetails(iD: Int): List<String> {
//        return mapIDtoDetails[iD]!!
//    }
}