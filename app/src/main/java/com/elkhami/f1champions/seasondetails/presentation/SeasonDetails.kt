package com.elkhami.f1champions.seasondetails.presentation

/**
 * Created by A.Elkhami on 22/05/2025.
 */

data class SeasonDetailsUiState(
    val isLoading: Boolean = false,
    val races: List<RaceItemUiState> = emptyList(),
    val errorMessage: String? = null,
    val seasonTitle: String = ""
)

data class RaceItemUiState(
    val roundText: String,
    val raceName: String,
    val dateText: String,
    val winnerName: String,
    val constructorName: String
)
