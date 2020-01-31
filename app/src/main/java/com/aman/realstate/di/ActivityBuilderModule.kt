package com.aman.realstate.di

import com.aman.realstate.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector()
    abstract fun mainActivityProvider() : MainActivity

}
