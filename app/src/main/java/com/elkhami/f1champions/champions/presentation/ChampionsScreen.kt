package com.elkhami.f1champions.champions.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elkhami.f1champions.R

/**
 * Created by A.Elkhami on 22/05/2025.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChampionsScreen(
    uiState: ChampionsUiState,
    onSeasonClick: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("F1 World Champions") })
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                uiState.errorMessage != null -> {
                    Text(
                        text = uiState.errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center),
                        textAlign = TextAlign.Center
                    )
                }

                else -> {
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(
                            items = uiState.champions,
                            key = { it.season }
                        ) { champion ->
                            ChampionItem(
                                item = champion,
                                onClick = { onSeasonClick(champion.season) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ChampionItem(
    item: ChampionItemUiState,
    onClick: () -> Unit
) {
    val seasonLabel = stringResource(id = R.string.label_season)
    val championLabel = stringResource(id = R.string.label_champion)
    val constructorLabel = stringResource(id = R.string.label_constructor)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "$seasonLabel: ${item.season}",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "$championLabel: ${item.driver}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "$constructorLabel: ${item.constructor}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ChampionsScreenPreview() {
    val previewState = ChampionsUiState(
        isLoading = false,
        champions = listOf(
            ChampionItemUiState(
                title = "2023",
                driver = "Max Verstappen",
                constructor = "Red Bull Racing",
                season = "2023"
            ),
            ChampionItemUiState(
                title = "022",
                driver = "Max Verstappen",
                constructor = "Red Bull Racing",
                season = "2022"
            ),
            ChampionItemUiState(
                title = "2021",
                driver = "Lewis Hamilton",
                constructor = "Mercedes",
                season = "2021"
            )
        )
    )

    ChampionsScreen(
        uiState = previewState,
        onSeasonClick = {}
    )
}

