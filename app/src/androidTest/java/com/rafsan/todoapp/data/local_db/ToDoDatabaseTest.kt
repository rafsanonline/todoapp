package com.rafsan.todoapp.data.local_db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.rafsan.todoapp.data.model.Todo
import com.rafsan.todoapp.data.getData
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ToDoDatabaseTest : TestCase() {

    private lateinit var db : TodoDatabase
    private lateinit var todoDao : TodoDao

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context.applicationContext, TodoDatabase::class.java).build()
        todoDao = db.todoDao()
    }

    @After
    fun closeDB() {
        db.close()
    }

    @Test
    fun insertAndGetData() = runBlocking {
        val data = Todo(title = "Test 1", description = "This is a test description for testing", completed = false)
        todoDao.insertTodo(data)
        val todo = todoDao.todoList().getData()
        assertThat(!todo[0].completed).isTrue()
    }

}