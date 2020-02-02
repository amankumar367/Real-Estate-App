package com.aman.realstate.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Entity(tableName = "exclusions")
@Parcelize
data class Exclusions(

    @ColumnInfo(name = "exclusions")
    @SerializedName("exclusions")
    @TypeConverters(ExclusionTypeConvertor::class)
    val exclusions: @RawValue List<Exclusion>
): Parcelable {

    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}