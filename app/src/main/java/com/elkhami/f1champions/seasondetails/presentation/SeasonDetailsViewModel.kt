package com.elkhami.f1champions.seasondetails.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elkhami.f1champions.seasondetails.domain.SeasonDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by A.Elkhami on 22/05/2025.
 */
@HiltViewModel
class SeasonDetailsViewModel @Inject constructor(
    private val repository: SeasonDetailsRepository
) : ViewModel() {

    var uiState by mutableStateOf(SeasonDetailsUiState())
        private set

    fun loadSeason(season: String) {
        if (uiState.races.isNotEmpty() || uiState.isLoading) return

        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, errorMessage = null)

            try {
                val results = repository.getRaceResults(season)

                val raceItems = results.map {
                    RaceItemUiState(
                        round = it.round,
                        raceName = it.raceName,
                        date = it.date,
                        winnerName = it.winner,
                        constructorName = it.constructor
                    )
                }

                uiState = uiState.copy(
                    isLoading = false,
                    races = raceItems,
                    seasonTitle = "Season $season"
                )

            } catch (e: Exception) {
                uiState = uiState.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Unexpected error"
                )
            }
        }
    }
}

