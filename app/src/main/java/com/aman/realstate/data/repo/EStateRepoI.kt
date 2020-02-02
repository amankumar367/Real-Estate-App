package com.aman.realstate.data.repo

import com.aman.realstate.room.entity.EState
import io.reactivex.Single

interface EStateRepoI {
    fun getData(key: String): Single<EState>
}