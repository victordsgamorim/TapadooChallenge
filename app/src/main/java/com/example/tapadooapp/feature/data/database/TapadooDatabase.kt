package com.example.tapadooapp.feature.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tapadooapp.feature.data.database.dao.BookDao
import com.example.tapadooapp.feature.data.database.entity.BookEntity

@Database(
    version = 1,
    entities = [BookEntity::class],
)
abstract class TapadooDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}