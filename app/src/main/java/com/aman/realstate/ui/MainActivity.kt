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
import com.aman.realstate.extensions.setIcon
import com.aman.realstate.extensions.showToast
import com.aman.realstate.room.entity.EState
import com.aman.realstate.room.entity.Facility
import com.aman.realstate.room.entity.Option
import com.aman.realstate.utils.ApiConstants
import com.google.android.material.chip.Chip
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
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

    private fun setData(data: EState) {
        setOptions(data.options, data.facilities)
    }

    private fun setOptions(options: List<Option>, facility: List<Facility>) {
        options.forEach {
            val chip =
                layoutInflater.inflate(R.layout.item_chip_category, null, false) as Chip

            if (it.facilityId == facility[0].facilityId!!) {
                chip.text = it.name
                chip.setIcon(it.icon)

                chip.setPadding(0, 0, 60, 0)

                chip.setOnCheckedChangeListener { compoundButton, b -> }
                cg_propery_type.addView(chip)
            }

            if (it.facilityId == facility[1].facilityId!!) {
                chip.text = it.name
                chip.setIcon(it.icon)

                chip.setPadding(0, 0, 60, 0)

                chip.setOnCheckedChangeListener { compoundButton, b -> }
                cg_rooms.addView(chip)
            }

            if (it.facilityId == facility[2].facilityId!!) {
                chip.text = it.name
                chip.setIcon(it.icon)

                chip.setPadding(0, 0, 60, 0)

                chip.setOnCheckedChangeListener { compoundButton, b -> }
                cg_other_facilities.addView(chip)
            }

        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
