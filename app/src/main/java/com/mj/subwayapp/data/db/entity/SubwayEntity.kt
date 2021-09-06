package com.mj.subwayapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

//노선
@Entity
data class SubwayEntity(
    @PrimaryKey val subwayId: Int
)
