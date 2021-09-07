package com.mj.subwayapp.data.db.entity.mapper

import com.mj.subwayapp.data.db.entity.StationWithSubwaysEntity
import com.mj.subwayapp.data.db.entity.SubwayEntity
import com.mj.subwayapp.domain.Station
import com.mj.subwayapp.domain.Subway

fun StationWithSubwaysEntity.toStation() = Station(
    name = station.stationName,
    isFavorite = station.isFavorite,
    connectedSubways = subways.toSubways()
)

fun List<StationWithSubwaysEntity>.toStations() = map { it.toStation() }

fun List<SubwayEntity>.toSubways(): List<Subway> = map { Subway.findById(it.subwayId) }