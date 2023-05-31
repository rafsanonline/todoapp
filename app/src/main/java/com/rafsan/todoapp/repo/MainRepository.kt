package com.rafsan.todoapp.repo

import androidx.paging.ExperimentalPagingApi
import com.rafsan.todoapp.data.local_db.TodoDatabase
import com.rafsan.todoapp.data.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


@ExperimentalPagingApi
class MainRepository @Inject constructor(private val database: TodoDatabase) {

    suspend fun addTodo(title: String, description: String, completed: Boolean) {

        withContext(Dispatchers.IO) {
            val todo = Todo(
                title = title,
                description = description,
                completed = completed
            )
            database.todoDao().insertTodo(todo)
        }

    }

    suspend fun updateValue(id: Long?, value: Boolean) {
        withContext(Dispatchers.IO) {
            if (id != null) {
                database.todoDao().updateItemById(id, value)
            }
        }
    }


}