package com.example.todolist_app_project.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist_app_project.LoginViewModel
import com.example.todolist_app_project.createManualEntryPage
import com.example.todolist_app_project.ui.theme.black

@Composable
fun NewEntryScreen(CreateManualEntryClick: () -> Unit, viewModel: LoginViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp),
    )
    {
        if (viewModel.error.value.isNotBlank()) {
            ErrorField(viewModel)
        }
//        render composable functions here
        ItemStack(CreateManualEntryClick)
    }
}

@Composable
fun ItemStack(CreateManualEntryClick: () -> Unit) {
    Row(
        Modifier.clickable( onClick = {
            CreateManualEntryClick()
        } )
    ) {
        Text(
            text = "Add product",
            style = TextStyle(
                fontSize = 16.sp,
            ),
        )
        Icon(
            Icons.Outlined.Add,
            contentDescription = "Add icon",
            modifier = Modifier
                .padding(start = 8.dp)
                .border(1.dp, color = black, shape = CircleShape)
        )
    }
}