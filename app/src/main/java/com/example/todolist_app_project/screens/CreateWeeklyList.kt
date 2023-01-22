package com.example.todolist_app_project.screens

import android.os.Build.VERSION_CODES.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalOf
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
import kotlinx.coroutines.launch

@Composable
fun CreateWeeklyListScreen(viewModel: LoginViewModel) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()


    Column(modifier = Modifier.padding(top = 0.dp), verticalArrangement = Arrangement.Center) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar {
                    Text("This is it")
                }
            }
        ) {

        }
    }
}



