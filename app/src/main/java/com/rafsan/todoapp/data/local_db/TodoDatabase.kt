package com.rafsan.todoapp.data.local_db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rafsan.todoapp.data.model.Todo


@Database(entities = [Todo::class],version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao() : TodoDao
}