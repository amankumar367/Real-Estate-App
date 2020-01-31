package com.aman.realstate.room.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aman.realstate.data.pojo.RealEState
import com.aman.realstate.room.dao.EStateDao

@Database(entities = [RealEState.Facility.Option::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun eStateDao(): EStateDao

    companion object {
        private const val TAG = "AppDatabase"
        private const val DATABASE_NAME = "real_estate.db"

        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(this) {
                    Log.d(TAG, " >>> Creating new database instance")
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                }
            }
            Log.d(TAG, " >>> Getting the database instance")
            return INSTANCE
        }

        fun destroyInstance() {
            Log.d(TAG, " >>> Destroying app database instance")
            INSTANCE = null
        }
    }
}