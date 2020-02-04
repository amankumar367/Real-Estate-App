package com.aman.realstate.ui

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.aman.realstate.R
import com.aman.realstate.data.repo.EStateRepoI
import com.aman.realstate.databinding.ActivityMainBinding
import com.aman.realstate.extensions.createFactory
import com.aman.realstate.extensions.showToast
import com.aman.realstate.room.entity.EState
import com.aman.realstate.room.entity.Exclusion
import com.aman.realstate.room.entity.Facility
import com.aman.realstate.room.entity.Option
import com.aman.realstate.ui.adapter.PropertyAdapter
import com.aman.realstate.ui.adapter.RecyclerViewItemClickListener
import com.aman.realstate.utils.ApiConstants
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity(), RecyclerViewItemClickListener {

    @Inject
    lateinit var repo: EStateRepoI

    private lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    private lateinit var propertyAdapter: PropertyAdapter

    private lateinit var roomAdapter: PropertyAdapter

    private lateinit var otherFacilityAdapter: PropertyAdapter

    private var disableExclusionList: List<Exclusion>? = null

    private var mState: EState? = null

    private var isItemSelected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        init()
        setObserver()
        loadData()
        onClick()

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

    private fun loadData() {
        viewModel.getData(ApiConstants.NETWORK_CALL)
    }

    private fun updateView(state: MainState) {
        if (state.success) setData(state.data!!)
    }

    private fun onClick() {
        btn_search.setOnClickListener {
            showToast("Search successful")
        }

        btn_retry.setOnClickListener {
           loadData()
        }
    }

    private fun setAlphaForExclusions(ids: List<String>) {
        mState?.let {
            var foundList: List<Exclusion>? = null
            it.exclusions.forEach { exclusions ->
                var isFound = false
                exclusions.exclusions.forEach { exclusion ->
                    if (exclusion.facility_id.toString() == ids[0]
                        && exclusion.options_id.toString() == ids[1]) {
                        isFound = true
                        return@forEach
                    }
                }

                if (isFound) {
                    foundList = exclusions.exclusions
                    return@forEach
                }
            }

            disableExclusionList = foundList?.let { list ->
                list.filter { exclusion ->
                    exclusion.facility_id.toString() != ids[0] && exclusion.options_id.toString() != ids[1]
                }
            }

            propertyAdapter.disableExclusionList = disableExclusionList
            roomAdapter.disableExclusionList = disableExclusionList
            otherFacilityAdapter.disableExclusionList = disableExclusionList

            propertyAdapter.notifyDataSetChanged()
            roomAdapter.notifyDataSetChanged()
            otherFacilityAdapter.notifyDataSetChanged()
            Log.d(TAG, "Searching ids: $ids & disableColorList list : $disableExclusionList")
        }
    }

    private fun setData(data: EState) {
        mState = data
        setPropertyOption(data.options, data.facilities)
        setNoRoomOption(data.options, data.facilities)
        setOtherOption(data.options, data.facilities)
    }

    private fun setPropertyOption(options: List<Option>, facility: List<Facility>) {
        rv_propery_type.layoutManager = GridLayoutManager(this, 4)
        val propertyOption = options.filter {
            it.facilityId == facility[0].facilityId
        }

        propertyAdapter = PropertyAdapter(propertyOption, null,this)
        rv_propery_type.adapter = propertyAdapter
    }

    private fun setNoRoomOption(options: List<Option>, facilities: List<Facility>) {
        rv_rooms.layoutManager = GridLayoutManager(this, 4)
        val roomOptions = options.filter {
            it.facilityId == facilities[1].facilityId
        }

        roomAdapter = PropertyAdapter(roomOptions, null, this)
        rv_rooms.adapter = roomAdapter

    }

    private fun setOtherOption(options: List<Option>, facilities: List<Facility>) {
        rv_other_facilities.layoutManager = GridLayoutManager(this, 4)
        val otherOptions = options.filter {
            it.facilityId == facilities[2].facilityId
        }
        otherFacilityAdapter = PropertyAdapter(otherOptions, null, this)
        rv_other_facilities.adapter = otherFacilityAdapter
    }

    override fun onItemSelected(options: Option) {
        setAlphaForExclusions(listOf(options.facilityId!!, options.id!!))
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
