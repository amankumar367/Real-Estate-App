package com.aman.realstate.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "facility")
@Parcelize
data class Facility(

    @ColumnInfo(name = "facility_id")
    @SerializedName("facility_id")
    val facilityId: String?,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String?

): Parcelable {
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}