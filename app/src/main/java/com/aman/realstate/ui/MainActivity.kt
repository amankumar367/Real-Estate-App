package com.aman.realstate.ui

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aman.realstate.R
import com.aman.realstate.data.repo.EStateRepoI
import com.aman.realstate.databinding.ActivityMainBinding
import com.aman.realstate.extensions.createFactory
import com.aman.realstate.utils.ApiConstants
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var repo: EStateRepoI

    private lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

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
            binding.state = it
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
        viewModel.getData(ApiConstants.NETWORK_CALL)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
