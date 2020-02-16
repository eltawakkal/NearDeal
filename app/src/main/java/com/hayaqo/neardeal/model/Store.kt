package com.hayaqo.neardeal.model

data class Store(
    val id: String,
    val name: String,
    val lat: String,
    val lng: String,
    val photo: String,
    val telp: String,
    val description: String,
    val open_hour: String,
    val address: String,
    val created_at: String
)