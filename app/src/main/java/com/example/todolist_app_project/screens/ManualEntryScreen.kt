package com.example.todolist_app_project.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolist_app_project.LoginViewModel


@Composable
fun CreateManualEntryScreen(CreateWeeklyListScreenClick: () -> Unit, viewModel: LoginViewModel) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()


    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        if (viewModel.error.value.isNotBlank()) {
            ErrorField(viewModel)
        }

//        render composable functions here


    }
}





















