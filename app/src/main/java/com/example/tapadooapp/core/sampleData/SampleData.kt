package com.example.tapadooapp.core.sampleData

import com.example.tapadooapp.feature.domain.model.Book

val sampleData = listOf(
    Book(
        id = 1,
        title = "The Name of the Wind",
        isbn = "9780756404741",
        price = 20,
        currencyCode = "EUR",
        author = "Patrick Rothfuss",
        isFav = true,
        imageCover = ""
    ),
    Book(
        id = 2,
        title = "A Game of Thrones",
        isbn = "9780553593716",
        price = 22,
        currencyCode = "EUR",
        author = "George R. R. Martin",
        isFav = false,
        imageCover = ""
    ),
)