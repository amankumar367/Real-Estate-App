package com.aman.realstate.data.pojo


import com.google.gson.annotations.SerializedName

data class RealEState(
    @SerializedName("exclusions")
    val exclusions: List<Any?>?,
    @SerializedName("facilities")
    val facilities: List<Facility?>?
) {
    data class Facility(
        @SerializedName("facility_id")
        val facilityId: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("options")
        val options: List<Option?>?
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