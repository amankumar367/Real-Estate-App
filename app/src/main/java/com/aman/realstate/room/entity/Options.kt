package com.aman.realstate.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "option")
data class Options(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "icon")
    @SerializedName("icon")
    val icon: String?,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String?
)