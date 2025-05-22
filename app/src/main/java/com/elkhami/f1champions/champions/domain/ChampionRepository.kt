package com.elkhami.f1champions.champions.domain

/**
 * Created by A.Elkhami on 22/05/2025.
 */
interface ChampionRepository {
    suspend fun getChampions(): List<Champion>
}
