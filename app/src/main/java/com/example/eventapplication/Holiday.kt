package com.example.eventapplication

data class Holiday(
    val country: Country,
    val date: Date,
    val description: String,
    val locations: String,
    val name: String,
    val type: List<String>,
    val urlid: String
)