package com.aman.realstate.data.repo

import android.util.Log
import com.aman.realstate.RealEStateApp.Companion.TAG
import com.aman.realstate.data.pojo.RealEState
import com.aman.realstate.network.ApiInterface
import com.aman.realstate.room.database.AppDatabase
import com.aman.realstate.room.entity.EState
import com.aman.realstate.room.entity.Exclusions
import com.aman.realstate.room.entity.Facility
import com.aman.realstate.room.entity.Option
import com.aman.realstate.utils.RateLimiter
import io.reactivex.Single
import java.util.concurrent.TimeUnit


class EStateRepo(
    private val apiInterface: ApiInterface,
    private val db: AppDatabase): EStateRepoI {

    private val rateLimit = RateLimiter<String>(24, TimeUnit.HOURS)
    private var exclusions: List<Exclusions> = listOf()
    private var facilities: List<Facility> = listOf()
    private var options: List<Option> = listOf()


    override fun getData(key: String): Single<EState> {
        return Single.create { emitter ->
            if (rateLimit.shouldFetch(key)) {
                try {
                    Log.d(TAG, " >>> Fetching data from network")
                    db.eStateDao().deleteAllExclusions()
                    db.eStateDao().deleteAllFacilities()
                    db.eStateDao().deleteAllOptions()

                    val response = apiInterface.getResponse().execute()
                    if (response.isSuccessful) {
                        response.body()?.let {
                            emitter.onSuccess(fetchAndStore(it))
                        }
                    } else {
                        rateLimit.reset(key)
                        emitter.onError(Exception("Failed to fetch data from remote"))
                    }
                } catch (exception: Exception) {
                    rateLimit.reset(key)
                    emitter.onError(exception)
                }
            } else {
                try {
                    Log.d(TAG, " >>> Fetching data from db")
                    exclusions = db.eStateDao().getExclusions()
                    facilities = db.eStateDao().getFacilities()
                    options = db.eStateDao().getOptions()

                    emitter.onSuccess(EState(exclusions, facilities, options))
                } catch (exception: Exception) {
                    emitter.onError(exception)
                }
            }
        }
    }

    private fun fetchAndStore(realEState: RealEState): EState {
        realEState.exclusions!!.forEach { list ->
            exclusions = exclusions + Exclusions(list!!)
        }

        realEState.facilities!!.forEach { list ->
            facilities = facilities + Facility(
                facilityId = list?.facilityId,
                name = list?.name)

            list?.options!!.forEach { optionList ->
                options = options + Option(
                    icon = optionList!!.icon!!,
                    id = optionList.id!!,
                    facilityId = list.facilityId,
                    name = optionList.name!!)
            }
        }
        db.eStateDao().insertExclusions(exclusions)
        db.eStateDao().insertFacilities(facilities)
        db.eStateDao().insertOptions(options)
        return EState(exclusions, facilities, options)
    }

}