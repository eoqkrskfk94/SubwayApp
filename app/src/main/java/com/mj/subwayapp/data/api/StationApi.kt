package com.mj.subwayapp.data.api

import com.mj.subwayapp.data.db.entity.StationEntity
import com.mj.subwayapp.data.db.entity.SubwayEntity

interface StationApi {

    suspend fun getStationDataUpdatedTimeMillis(): Long

    suspend fun getStationSubways(): List<Pair<StationEntity, SubwayEntity>>

}