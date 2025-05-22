package com.elkhami.f1champions.seasondetails.domain

/**
 * Created by A.Elkhami on 22/05/2025.
 */
interface SeasonDetailsRepository {
    suspend fun getRaceResults(season: String): List<SeasonRaceResult>
}