package com.aman.realstate.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aman.realstate.data.repo.EStateRepoI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val repo: EStateRepoI): ViewModel() {

    var stateObservable: MutableLiveData<MainState> = MutableLiveData()

    private val compositeDisposable = CompositeDisposable()

    var state = MainState()
        set(value) {
            field = value
            publishState(value)
        }

    fun getData() {
        Log.d(TAG, " >>> Receive call for fetching all data")
        state = state.copy(loading = true)

        compositeDisposable.add(
            repo.getData("Network")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    state = state.copy(loading = false, success = true, failure = false, data = it)
                }, {
                    state = state.copy(loading = false, success = false, failure = true,
                        message = it.localizedMessage)
                })
        )


    }

    private fun publishState(state: MainState) {
        Log.d(TAG," >>> Publish State : $state")
        stateObservable.value = state
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, " >>> Clearing compositeDisposable")
        compositeDisposable.clear()
    }

    companion object {
        const val TAG = "MainViewModel"
    }
}