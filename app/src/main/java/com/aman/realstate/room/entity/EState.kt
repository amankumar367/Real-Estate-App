package com.aman.realstate.room.entity

data class EState(
    val exclusions: List<Exclusions> = listOf(),
    val facilities: List<Facility> = listOf(),
    val options: List<Option> = listOf()
)