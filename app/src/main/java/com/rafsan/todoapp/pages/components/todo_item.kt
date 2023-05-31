package com.rafsan.todoapp.pages.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rafsan.todoapp.R

@Composable
fun TodoItemList(todoName: String, isCompleted: Boolean, onSelect: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .height(50.dp),
        backgroundColor = Color.White,
        shape = RoundedCornerShape(50.dp),
        elevation = 8.dp
    ) {

        Row(
            modifier = Modifier.padding(start = 20.dp, end = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = todoName,
                style = TextStyle(
                    fontSize = 15.sp,
                    textDecoration = setTextDecoration(isCompleted),
                    color = setTextColor(isCompleted)
                )
            )

            if (isCompleted) {
                Image(
                    painter = painterResource(id = R.drawable.ic_completed),
                    contentDescription = "Image",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            onSelect()
                    }
                )
            } else {
                Card(
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            onSelect()
                        },
                    backgroundColor = Color.White,
                    shape = CircleShape,
                    elevation = 8.dp
                ) {}
            }

            // Content inside the white container
        }
    }
}

fun setTextColor(completed: Boolean): Color {
    return if (completed) {
        Color.Gray
    } else {
        Color.Black
    }

}

fun setTextDecoration(completed: Boolean): TextDecoration? {
 return if (completed) {
      TextDecoration.LineThrough
 } else {
     TextDecoration.None
 }
}
