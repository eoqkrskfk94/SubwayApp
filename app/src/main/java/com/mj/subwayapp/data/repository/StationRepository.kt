package com.mj.subwayapp.data.repository

import com.mj.subwayapp.domain.ArrivalInformation
import com.mj.subwayapp.domain.Station
import kotlinx.coroutines.flow.Flow

interface StationRepository {

    val stations: Flow<List<Station>>

    suspend fun refreshStations()

    suspend fun getStationArrivals(stationName: String) : List<ArrivalInformation>

    suspend fun updateStation(station: Station)
}