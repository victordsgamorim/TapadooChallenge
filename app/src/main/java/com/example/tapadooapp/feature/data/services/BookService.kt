package com.example.tapadooapp.feature.data.services

import com.example.tapadooapp.feature.data.database.entity.BookEntity
import com.example.tapadooapp.feature.domain.model.Book
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

data class BookResponse(
    val id: Int,
    val title: String,
    val isbn: String,
    val price: Int,
    val currencyCode: String,
    val author: String
)

fun BookResponse.toBookEntity(): BookEntity {
    return BookEntity(
        id = id,
        title = title,
        isbn = isbn,
        price = price,
        currencyCode = currencyCode,
        author = author,
        isFav = false,
    )
}

fun BookEntity.toBook(): Book {
    return Book(
        id = id,
        title = title,
        isbn = isbn,
        price = price,
        currencyCode = currencyCode,
        author = author,
        isFav = isFav,
        imageCover = "https://fastly.picsum.photos/id/130/1000/800.jpg?hmac=yGv_U0qPGNhPwghqNChlgKUURvY1HZtm4r0ooC98W8c"
    )
}

interface BookService {

    @GET("books")
    suspend fun getBooks(): Response<List<BookResponse>>

    @GET("books/{id}")
    suspend fun getBookById(@Path("id") id: Int): Response<BookResponse>
}

