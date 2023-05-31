package com.rafsan.todoapp.data.local_db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rafsan.todoapp.data.model.Todo


@Dao
interface TodoDao {
    @Query("SELECT * FROM todo_table")
    fun todoList() : PagingSource<Int, Todo>

    @Query("UPDATE todo_table SET completed = :value WHERE id = :itemId")
    suspend fun updateItemById(itemId: Long, value: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)

}