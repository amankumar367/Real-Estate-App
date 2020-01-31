package com.aman.realstate.network

import com.aman.realstate.data.pojo.RealEState
import com.aman.realstate.utils.ApiConstants
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET(ApiConstants.PATH)
    fun getResponse(): Call<RealEState>
}