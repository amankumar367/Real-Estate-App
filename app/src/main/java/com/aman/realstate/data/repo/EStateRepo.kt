package com.aman.realstate.data.repo

import com.aman.realstate.data.pojo.RealEState
import com.aman.realstate.network.ApiInterface
import com.aman.realstate.room.database.AppDatabase
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EStateRepo(
    private val apiInterface: ApiInterface,
    private val db: AppDatabase): EStateRepoI {

    override fun getData(): Single<RealEState> {
        return Single.create<RealEState> { emitter ->
            apiInterface.getResponse().enqueue(object : Callback<RealEState> {

                override fun onResponse(call: Call<RealEState>, response: Response<RealEState>) {
                    response.body()?.let {
                        emitter.onSuccess(it)
                    }
                }

                override fun onFailure(call: Call<RealEState>, t: Throwable) {
                    emitter.onError(t)
                }
            })
        }
    }

}