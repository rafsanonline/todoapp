package com.rafsan.todoapp.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import com.rafsan.todoapp.pages.AddTodo
import com.rafsan.todoapp.pages.TodoList
import com.rafsan.todoapp.ui.theme.GalleryAppTheme
import com.rafsan.todoapp.view_model.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalPagingApi::class)
    private val viewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalPagingApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            runApplication(viewModel)
        }
    }

    @ExperimentalPagingApi
    @Composable
    private fun runApplication(viewModel: MainViewModel) {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "add_todo", builder = {
            composable("todo_list", content = { TodoList(navController, viewModel) })
            composable("add_todo", content = { AddTodo(navController, viewModel) })
        })
    }
}


