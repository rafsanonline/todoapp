package com.rafsan.todoapp.view_model

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rafsan.todoapp.data.local_db.TodoDatabase
import com.rafsan.todoapp.data.model.Todo
import com.rafsan.todoapp.repo.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@ExperimentalPagingApi
@HiltViewModel
class MainViewModel @Inject constructor(
    private var mainRepository: MainRepository,
    var todoDatabase: TodoDatabase
):  ViewModel() {

    suspend fun addTodo (title: String, description: String, completed: Boolean) {
        mainRepository.addTodo(title = title, description = description, completed = completed);
    }

    fun getTodoList(): Flow<PagingData<Todo>> {
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 1),
            pagingSourceFactory = { todoDatabase.todoDao().todoList()}
        ).flow
    }

    suspend fun updateValue(id: Long?, value: Boolean) {
        mainRepository.updateValue(id, value)
    }
}