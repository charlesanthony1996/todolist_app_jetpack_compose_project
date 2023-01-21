package com.example.todolist_app_project.screens

import android.os.Build.VERSION_CODES.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.todolist_app_project.LoginViewModel
import com.example.todolist_app_project.NavigateBetweenScreen
import com.example.todolist_app_project.NavigationEnum
import com.example.todolist_app_project.R




@Composable
fun WelcomeScreen(viewModel: LoginViewModel) {
    Column(modifier = Modifier.padding(top = 8.dp)) {
        WelcomeText()
        LogoutButton(viewModel)
    }
    Column(modifier = Modifier.padding(top = 0.dp)) {
        Scaffold(
            bottomBar = {
                BottomAppBar{
                    Button(onClick= {/* */ },
                    contentPadding = PaddingValues(start=0.dp, bottom=0.dp, end=0.dp, top=0.dp)
                    ) {
                        Icon(
                            Icons.Filled.Menu,
                            contentDescription = "Menu",
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                    }
            }
            }
        ) {

        }
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { viewModel.signOut() }) {
            Text(text = "Log out")
        }
    }
}


