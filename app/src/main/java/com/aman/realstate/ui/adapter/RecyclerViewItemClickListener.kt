package com.aman.realstate.ui.adapter

import com.aman.realstate.room.entity.Option

interface RecyclerViewItemClickListener {
    fun onItemSelected(options: Option, isClickable: Boolean)
}