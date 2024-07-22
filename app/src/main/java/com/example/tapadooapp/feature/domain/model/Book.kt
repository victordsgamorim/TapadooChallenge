package com.example.tapadooapp.feature.domain.model

data class Book(
    val id: Int,
    val title: String,
    val isbn: String,
    val price: Int,
    val currencyCode: String,
    val author: String,
    val isFav: Boolean,
    val imageCover: String,
)

