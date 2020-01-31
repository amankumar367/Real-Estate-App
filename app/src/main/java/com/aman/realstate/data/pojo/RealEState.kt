package com.aman.realstate.data.pojo


import androidx.room.ColumnInfo
import androidx.room.Entity
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

        @Entity(tableName = "option")
        data class Option(
            @ColumnInfo(name = "icon")
            @SerializedName("icon")
            val icon: String?,

            @ColumnInfo(name = "id")
            @SerializedName("id")
            val id: String?,

            @ColumnInfo(name = "name")
            @SerializedName("name")
            val name: String?
        )
    }
}