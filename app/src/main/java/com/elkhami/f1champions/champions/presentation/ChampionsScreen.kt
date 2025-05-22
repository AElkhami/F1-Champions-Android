package com.elkhami.f1champions.champions.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.elkhami.f1champions.R
import com.elkhami.f1champions.core.ui.theme.LocalDimens

/**
 * Created by A.Elkhami on 22/05/2025.
 */

@Composable
fun ChampionsScreen(
    viewModel: ChampionsViewModel = hiltViewModel(),
    onSeasonClick: (String) -> Unit
) {
    LaunchedEffect(true) {
        viewModel.loadChampions()
    }

    ChampionsScreenContent(
        uiState = viewModel.uiState,
        onSeasonClick = onSeasonClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChampionsScreenContent(
    uiState: ChampionsUiState,
    onSeasonClick: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Icon(
                    painter = painterResource(R.drawable.ic_f1),
                    tint = Color.Red,
                    contentDescription = null
                )
            })
        }
    ) { innerPadding ->
        val dimens = LocalDimens.current

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
                        contentPadding = PaddingValues(dimens.medium),
                        verticalArrangement = Arrangement.spacedBy(dimens.verticalSpace)
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
    val dimens = LocalDimens.current
    val seasonLabel = stringResource(id = R.string.label_season)
    val championLabel = stringResource(id = R.string.label_champion)
    val constructorLabel = stringResource(id = R.string.label_constructor)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(dimens.medium),
        elevation = CardDefaults.cardElevation(defaultElevation = dimens.elevation),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(dimens.medium)
        ) {

            Icon(
                painter = painterResource(R.drawable.ic_f1),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(dimens.f1IconSize)
            )

            Column {
                Text(
                    text = "$seasonLabel: ${item.season}",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(dimens.small))
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

    ChampionsScreenContent(
        uiState = previewState,
        onSeasonClick = {}
    )
}

