package com.example.todolist_app_project.screens

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.navigation.NavHostController
import com.example.todolist_app_project.NavigationEnum
import com.example.todolist_app_project.screens.*
import com.example.todolist_app_project.R


@Composable
fun TopBar(navController: NavHostController, currentScreen: NavigationEnum) {
    TopAppBar(
        title = { Text(text = stringResource(currentScreen.title)) },

        navigationIcon = {
            println("$currentScreen")
            if (currentScreen != NavigationEnum.Home && currentScreen != NavigationEnum.Landing) {
                NavigateBackButton(navController)
            } else if (currentScreen == NavigationEnum.Home) {

            }
        }
    )
}

@Composable
fun NavigateBackButton(navController: NavHostController) {
    IconButton(onClick = { navController.popBackStack() },
        modifier = Modifier.semantics { contentDescription = "back button" }) {

        Icon(Icons.Filled.ArrowBack, stringResource(R.string.back_icon))
    }
}