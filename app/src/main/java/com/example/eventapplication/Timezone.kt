package com.example.eventapplication

data class Timezone(
    val offset: String,
    val zoneabb: String,
    val zonedst: Int,
    val zoneoffset: Int,
    val zonetotaloffset: Int
)