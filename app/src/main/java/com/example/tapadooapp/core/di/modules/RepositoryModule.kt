package com.example.tapadooapp.core.di.modules

import com.example.tapadooapp.feature.data.database.dao.BookDao
import com.example.tapadooapp.feature.data.services.BookService
import com.example.tapadooapp.feature.domain.repository.BookRepository
import com.example.tapadooapp.feature.domain.repository.BookRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideBookRepository(dao: BookDao, service: BookService): BookRepository {
        return BookRepositoryImpl(dao = dao, service = service);
    }
}