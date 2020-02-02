package com.aman.realstate.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "option")
@Parcelize
data class Option(
    @ColumnInfo(name = "icon")
    @SerializedName("icon")
    val icon: String?,

    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: String?,

    @ColumnInfo(name = "facility_id")
    @SerializedName("facility_id")
    val facilityId: String?,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String?

): Parcelable {
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "option_id")
    var optionId: Int = 0
}