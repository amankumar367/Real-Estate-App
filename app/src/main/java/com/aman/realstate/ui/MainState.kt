package com.aman.realstate.ui

import com.aman.realstate.room.entity.EState

data class MainState(
    var loading: Boolean = false,
    var success: Boolean = false,
    var failure: Boolean = false,
    var message: String? = null,
    var data: EState? = null
)