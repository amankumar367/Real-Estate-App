package com.aman.realstate.ui

data class MainState(
    var loading: Boolean = false,
    var success: Boolean = false,
    var failure: Boolean = false,
    var message: String? = null,
    var data: Any? = null
)