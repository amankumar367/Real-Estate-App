package com.aman.realstate.room.dao

import androidx.room.Dao
import androidx.room.Insert
import com.aman.realstate.room.entity.Options

@Dao
interface EStateDao {

    @Insert
    fun insetOptions(options: List<Options>)
}