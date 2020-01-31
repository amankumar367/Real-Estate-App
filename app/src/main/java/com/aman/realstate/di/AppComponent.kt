package com.aman.realstate.di

import android.app.Application
import com.aman.realstate.RealEStateApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuilderModule::class,
    RoomModule::class,
    RepoModule::class,
    NetworkModule::class
])
interface AppComponent: AndroidInjector<RealEStateApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}