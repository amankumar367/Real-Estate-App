package com.aman.realstate.data.pojo

import com.aman.realstate.room.entity.Exclusion
import com.google.gson.annotations.SerializedName

data class RealEState(
    @SerializedName("exclusions")
    val exclusions: List<List<Exclusion>?>? = listOf(),
    @SerializedName("facilities")
    val facilities: List<Facility?>? = listOf()
) {
    data class Facility(
        @SerializedName("facility_id")
        val facilityId: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("options")
        val options: List<Option?>? = listOf()
    ) {
        data class Option(
            @SerializedName("icon")
            val icon: String?,
            @SerializedName("id")
            val id: String?,
            @SerializedName("name")
            val name: String?
        )
    }
}