package com.aman.realstate.room.entity

import com.google.gson.annotations.SerializedName

data class Exclusion(
    @SerializedName("facility_id")
    val facility_id:Int,
    @SerializedName("options_id")
    val options_id:Int
)