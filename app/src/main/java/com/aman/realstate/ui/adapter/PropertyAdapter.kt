package com.aman.realstate.ui.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.aman.realstate.R
import com.aman.realstate.extensions.setImage
import com.aman.realstate.room.entity.Exclusion
import com.aman.realstate.room.entity.Option
import kotlinx.android.synthetic.main.layout_property.view.*


class PropertyAdapter(private val optionsList: List<Option>,
                      var disableExclusionList: List<Exclusion>?,
                      private val listener: RecyclerViewItemClickListener):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PropertyVH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_property, parent, false), listener)
    }

    override fun getItemCount(): Int {
        return optionsList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PropertyVH).bind(optionsList[position], position)
    }

    inner class PropertyVH(private val view: View,
                     private val listener: RecyclerViewItemClickListener)
        : RecyclerView.ViewHolder(view) {

        private var position: Int? = null
        private var disablePosition: Int? = -1
        fun bind(
            option: Option,
            position: Int
        ) {
            this.position = position
            view.tv_property_title.text = option.name
            view.iv_propety.setImage(option.icon)

            when {
                selectedPosition == position -> {
                    changeItemColor()
                }
                disablePosition == position -> {
                    backToNormal()
                    disablePosition = -1
                }
                else -> {
                    backToNormal()
                }
            }

            disableExclusionList?.let {
                it.forEach { exclusion ->
                    if (option.id == exclusion.options_id.toString()) {
                        disableItem()
                        disablePosition = position
                    }
                }
            }

            onClick(option)
        }

        private fun onClick(option: Option) {
            view.setOnClickListener {
                if (disablePosition != position) {
                    if (selectedPosition != position) {
                        notifyItemChanged(selectedPosition)
                        selectedPosition = position!!
                    }
                    listener.onItemSelected(option, true)
                } else {
                    listener.onItemSelected(option, false)
                }
            }
        }

        private fun backToNormal() {
            view.tv_property_title.setTextColor(ContextCompat.getColor(view.context, android.R.color.background_dark))
            view.iv_propety.imageTintList =
                ColorStateList.valueOf(ContextCompat.getColor(view.context, android.R.color.background_dark))
        }

        private fun changeItemColor() {
            view.tv_property_title.setTextColor(ContextCompat.getColor(view.context, R.color.colorAccent))
            view.iv_propety.imageTintList =
                ColorStateList.valueOf(ContextCompat.getColor(view.context, R.color.colorAccent))
        }

        private fun disableItem() {
            view.tv_property_title.setTextColor(ContextCompat.getColor(view.context, R.color.grey))
            view.iv_propety.imageTintList =
                ColorStateList.valueOf(ContextCompat.getColor(view.context, R.color.grey))
        }
    }
}