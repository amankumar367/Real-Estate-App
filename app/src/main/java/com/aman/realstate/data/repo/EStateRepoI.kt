package com.aman.realstate.data.repo

import com.aman.realstate.data.pojo.RealEState
import io.reactivex.Single

interface EStateRepoI {
    fun getData(): Single<RealEState>
}