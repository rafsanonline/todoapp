package com.rafsan.todoapp.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.rememberCoroutineScope

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import com.rafsan.todoapp.R
import com.rafsan.todoapp.pages.components.AppTopBar
import com.rafsan.todoapp.pages.components.TodoItemList
import com.rafsan.todoapp.view_model.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagingApi::class)
@Composable
fun TodoList(navController: NavHostController, viewModel: MainViewModel) {

    val data = viewModel.getTodoList().collectAsLazyPagingItems()

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar ={ AppTopBar(title = "To-do list")},
    ) {
        
        Box(modifier = Modifier.fillMaxWidth()) {
            LazyColumn(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(data.itemCount) { index ->
                    TodoItemList(todoName = data[index]?.title.toString(), isCompleted =  data[index]?.completed!!) {
                        coroutineScope.launch {
                            if (data[index]?.completed == false) {
                                viewModel.updateValue(data[index]?.id, true)
                            }
                        }
                    }
                }
            }
            
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.White)
                    )
                )
                .align(Alignment.BottomCenter)) {

                Image(
                    painter = painterResource(id = R.drawable.ic_add_todo),
                    contentDescription = "Image",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(bottom = 25.dp)
                        .align(alignment = Alignment.BottomCenter)
                        .clickable {
                            navController.popBackStack()
                        }
                )
                
            }

         }
        }
    }

