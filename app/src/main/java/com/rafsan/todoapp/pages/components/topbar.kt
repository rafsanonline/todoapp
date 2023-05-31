package com.rafsan.todoapp.pages.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rafsan.todoapp.R

@Composable
fun AppTopBar(title: String) {
    Box(
        modifier = Modifier
            .padding(start = 15.dp)
            .fillMaxWidth()
            .height(60.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_more),
            contentDescription = "Image",
            modifier = Modifier
                .size(25.dp)
                .align(alignment = Alignment.CenterStart)
        )
        Text(
            text = title,
            modifier = Modifier.align(Alignment.Center),
            style = TextStyle(
                fontSize = 20.sp
            )
        )
    }
}