package com.example.todolist_app_project.screens

import android.os.Build.VERSION_CODES.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.todolist_app_project.LoginViewModel
import com.example.todolist_app_project.R

@Composable
fun WelcomeScreen(viewModel: LoginViewModel) {
    Column(modifier = Modifier.padding(top = 8.dp)) {
        WelcomeText()
        LogoutButton(viewModel)
    }
}
@Composable
fun WelcomeText() {
    Text(
        text = "You are logged in",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h4
    )
}


@Composable
fun LogoutButton(viewModel: LoginViewModel) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { viewModel.signOut() }) {
            Text(text = "Log out")
        }
    }
}
