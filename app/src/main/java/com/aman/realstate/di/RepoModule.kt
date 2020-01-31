package com.aman.realstate.di

import com.aman.realstate.data.repo.EStateRepo
import com.aman.realstate.data.repo.EStateRepoI
import com.aman.realstate.network.ApiInterface
import com.aman.realstate.room.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Provides
    @Singleton
    fun provideEStateRepo(apiInterface: ApiInterface, appDatabase: AppDatabase): EStateRepoI {
        return EStateRepo(apiInterface, appDatabase)
    }
}