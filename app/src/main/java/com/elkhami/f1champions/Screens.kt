package com.elkhami.f1champions

/**
 * Created by A.Elkhami on 22/05/2025.
 */

sealed class Screen(val route: String) {
    object Champions : Screen("champions")
    object SeasonDetails : Screen("season_details/{season}") {
        fun createRoute(season: String) = "season_details/$season"
    }
}
