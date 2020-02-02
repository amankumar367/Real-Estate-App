package com.aman.realstate.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aman.realstate.room.entity.Exclusion
import com.aman.realstate.room.entity.Exclusions
import com.aman.realstate.room.entity.Facility
import com.aman.realstate.room.entity.Option

@Dao
interface EStateDao {

    @Insert
    fun insertFacilities(facilities: List<Facility>)

    @Insert
    fun insertOptions(options: List<Option>)

    @Insert
    fun insertExclusions(exclusions: List<Exclusions>)

    @Query("SELECT * FROM FACILITY")
    fun getFacilities(): List<Facility>

    @Query("SELECT * FROM OPTION")
    fun getOptions(): List<Option>

    @Query("SELECT * FROM EXCLUSIONS")
    fun getExclusions(): List<Exclusions>

    @Query("DELETE FROM FACILITY")
    fun deleteAllFacilities()

    @Query("DELETE FROM OPTION")
    fun deleteAllOptions()

    @Query("DELETE FROM EXCLUSIONS")
    fun deleteAllExclusions()

}