package com.rafsan.todoapp.pages

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import com.rafsan.todoapp.pages.components.AppTopBar
import com.rafsan.todoapp.view_model.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagingApi::class, ExperimentalComposeUiApi::class)
@Composable
fun AddTodo(navController: NavHostController, viewModel: MainViewModel) {

    var todoName by remember {
        mutableStateOf("")
    }

    var todoDescription by remember {
        mutableStateOf("")
    }

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = { AppTopBar(title = "Add a to-do")}
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(
                value = todoName,
                onValueChange = {
                    todoName = it
                },
                label = { Text("To-do Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = todoDescription,
                onValueChange = {
                    todoDescription = it
                },
                label = { Text("Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(bottom = 16.dp),
                keyboardOptions = KeyboardOptions(imeAction = androidx.compose.ui.text.input.ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                })
            )
            Button(
                onClick = {

                  coroutineScope.launch {
                      when {
                          todoName.isEmpty() -> {
                             Toast.makeText(context,"Enter a todo name", Toast.LENGTH_SHORT).show()
                          }
                          todoDescription.isEmpty() -> {
                              Toast.makeText(context,"Enter a todo description", Toast.LENGTH_SHORT).show()
                          }
                          else -> {
                              viewModel.addTodo(
                                  title = todoName,
                                  description = todoDescription,
                                  completed = false
                              )

                              navController.navigate("todo_list")
                          }
                      }

                  }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Add")
            }
        }
    }
}