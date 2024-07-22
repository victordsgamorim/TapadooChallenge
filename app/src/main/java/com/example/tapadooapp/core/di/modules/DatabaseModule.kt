package com.example.tapadooapp.core.di.modules

import android.content.Context
import androidx.room.Room
import com.example.tapadooapp.feature.data.database.TapadooDatabase
import com.example.tapadooapp.feature.data.database.dao.BookDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): TapadooDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = TapadooDatabase::class.java,
            name = "tapadoo.db",
        ).build()
    }

    @Provides
    fun provideBookDao(db: TapadooDatabase): BookDao {
        return db.bookDao()
    }
}