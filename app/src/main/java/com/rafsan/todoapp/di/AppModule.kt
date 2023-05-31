package com.rafsan.todoapp.di

import android.app.Application
import androidx.room.Room
import com.rafsan.todoapp.data.local_db.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): TodoDatabase =
        Room.databaseBuilder(app, TodoDatabase::class.java, "todo_db")
            .build()

}