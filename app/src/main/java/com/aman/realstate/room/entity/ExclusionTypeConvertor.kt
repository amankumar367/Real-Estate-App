package com.aman.realstate.room.entity

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ExclusionTypeConvertor {
    @TypeConverter
    fun toJSON(exclusions: List<Exclusion>): String {
        return Gson().toJson(exclusions)
    }

    @TypeConverter
    fun toExlusions(exclusions: String): List<Exclusion> {
        val type = object :TypeToken<List<Exclusion>>(){}.type
        return Gson().fromJson(exclusions,type)
    }
}
