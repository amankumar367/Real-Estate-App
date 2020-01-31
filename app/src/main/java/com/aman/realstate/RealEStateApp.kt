package com.aman.realstate

import android.util.Log
import com.aman.realstate.di.AppComponent
import com.aman.realstate.di.DaggerAppComponent
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class RealEStateApp: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {

        Log.d(TAG, " >>> RealEStateApp Created")

        if (BuildConfig.DEBUG) {
            Log.d(TAG, " >>> Initializing Stetho")
            Stetho.initializeWithDefaults(this)
        }

        appComponent = DaggerAppComponent.builder().application(this).build()

        return appComponent
    }

    companion object {
        const val TAG = "RealEStateApp"

        private var appComponent: AppComponent? = null

        fun getAppComponent(): AppComponent? {
            return appComponent
        }
    }
}