package com.aman.realstate.ui

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aman.realstate.R
import com.aman.realstate.data.repo.EStateRepoI
import com.aman.realstate.extensions.createFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var repo: EStateRepoI

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        setObserver()
        loadData()
    }

    private fun init() {
        Log.d(TAG, " >>> Initializing viewModel")

        val factory = MainViewModel(repo).createFactory()
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

    private fun setObserver() {
        viewModel.stateObservable.observe( this, Observer {
            updateView(it)
        })
    }

    private fun updateView(state: MainState) {
        when {
            state.loading -> {}
            state.success -> {}
            state.failure -> {}
        }
    }

    private fun loadData() {
        viewModel.getData()
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
