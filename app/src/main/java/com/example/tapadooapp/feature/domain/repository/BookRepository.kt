package com.example.tapadooapp.feature.domain.repository

import android.util.Log
import com.example.tapadooapp.feature.data.database.dao.BookDao
import com.example.tapadooapp.feature.data.services.BookService
import com.example.tapadooapp.feature.data.services.toBook
import com.example.tapadooapp.feature.data.services.toBookEntity
import com.example.tapadooapp.feature.domain.model.Book
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

interface BookRepository {
    suspend fun getBooks(): Flow<List<Book>>
    suspend fun getBookById(id: Int): Flow<Book>
    suspend fun searchBooks(query: String): Flow<List<Book>>
    suspend fun getFavouriteBooks(): Flow<List<Book>>
    suspend fun setAsFavourite(id: Int)
}

class BookRepositoryImpl @Inject constructor(
    private val service: BookService,
    private val dao: BookDao
) : BookRepository {
    override suspend fun getBooks(): Flow<List<Book>> {

        CoroutineScope(coroutineContext).launch {
            try {
                val response = service.getBooks()
                if (response.isSuccessful) {
                    val entities = response.body()!!.map { it.toBookEntity() }
                    dao.insertAll(*entities.toTypedArray())
                } else {
                    Log.e("BookRepository", "getAll: Fail to connect to API")
                }
            } catch (e: ConnectException) {
                Log.e("BookRepository", "getAll: Internet connection not working")
            }
        }

        return dao.getAll().map { entities ->
            entities.map { it.toBook() }
        }
    }

    override suspend fun getBookById(id: Int): Flow<Book> {
        CoroutineScope(coroutineContext).launch {
            try {
                val response = service.getBookById(id)
                if (response.isSuccessful) {
                    val book = response.body()!!.toBookEntity()
                    dao.insert(book)
                } else {
                    Log.e("BookRepository", "getBookById: Fail to connect to API")
                }
            } catch (e: ConnectException) {
                Log.e("BookRepository", "getBookById: Internet connection not working")
            }
        }

        return dao.getById(id).map { it.toBook() }
    }

    override suspend fun searchBooks(query: String): Flow<List<Book>> {
        return dao.searchBooks(query).map { entities ->
            entities.map { it.toBook() }
        }
    }

    override suspend fun getFavouriteBooks(): Flow<List<Book>> {
        return dao.getFavouriteBooks().map { entities ->
            entities.map { it.toBook() }
        }
    }

    override suspend fun setAsFavourite(id: Int) {
        dao.insertToFavorite(id)
    }
}