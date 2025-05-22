package com.elkhami.f1champions.champions.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by A.Elkhami on 22/05/2025.
 */

@HiltViewModel
class ChampionsViewModel @Inject constructor(
) : ViewModel() {

    var uiState by mutableStateOf(ChampionsUiState())
        private set

}