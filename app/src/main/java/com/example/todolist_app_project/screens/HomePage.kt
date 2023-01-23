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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
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
fun WelcomeScreen(CreateWeeklyListScreenClick: () -> Unit, viewModel: LoginViewModel) {
    var isDrawerOpen = false
    val navController = rememberNavController()

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.padding(top = 0.dp), verticalArrangement = Arrangement.Center) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar {
                    Button(onClick = { /* */ },
                    contentPadding = PaddingValues(start=0.dp, end=0.dp)
                    ) {
                        Icon(
                            Icons.Filled.Menu,
                            contentDescription = "Menu",
                            modifier = Modifier
                                .size(ButtonDefaults.IconSize)
                                .clickable(onClick = {
                                    scope.launch {
                                        scaffoldState.drawerState.apply {
                                            if (isClosed) open() else close()
                                        }
                                    }
                                })
                        )
                    }
                }
            },
//            drawer content here
            drawerContent = {
                            Column{
                                Card(modifier = Modifier.fillMaxWidth(),)
                                {
                                    Icon(
                                        Icons.Outlined.AccountCircle,
                                        contentDescription = "User profile picture",
                                        modifier = Modifier.size(50.dp)
                                    )
                                }
                                Divider()
                                Spacer(modifier = Modifier.fillMaxWidth())
                                Button(
                                    onClick = { /* */ },
                                    contentPadding = PaddingValues(
                                        start = 0.dp, end = 0.dp
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("Profile")
                                }
                                Divider()
                                Button(
                                    onClick = { /* */ },
                                    contentPadding = PaddingValues(
                                        start = 0.dp, end= 0.dp
                                    ),
                                modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("My items")
                                }
                                Divider()
                                Button(
                                    onClick = { /* */ },
                                    contentPadding = PaddingValues(
                                        start = 0.dp, end = 0.dp
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("Overview")
                                }
                                Divider()
                                Button(
                                    onClick = { /* */ },
                                    contentPadding = PaddingValues(
                                        start = 0.dp, end = 0.dp
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("External links")
                                }
                                Divider()
                                Button(
                                    onClick = { /* */ },
                                    contentPadding = PaddingValues(
                                        start = 0.dp, end = 0.dp
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("Configuration")
                                }
                                Divider()
                                Button(
                                    onClick = { /* */ },
                                    contentPadding = PaddingValues(
                                        start = 0.dp, end = 0.dp
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("About")
                                }
                                Divider()
                                Button(
                                    onClick = { viewModel.signOut() },
                                    contentPadding = PaddingValues(
                                        start = 0.dp, end = 0.dp
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("Logout")
                                }

                            }
            },
            bottomBar = {
                BottomAppBar{
                    val buttonWidth = 100.dp
                    CreateButton(buttonWidth, CreateWeeklyListScreenClick)
                }
            },
            floatingActionButton = {
                FloatingActionButton(onClick= { /**/ },
                    modifier= Modifier.padding(0.dp),
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "create a weekly list",
                        modifier = Modifier.size(ButtonDefaults.IconSize),
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.Center

        ) {
            Card(modifier = Modifier
                .height(100.dp)
                .width(500.dp)
                .border(1.dp, MaterialTheme.colors.onSecondary)
                .padding(0.dp),
            elevation = 10.dp) {
                Column (
//                    your card content here
                    modifier = Modifier.padding(0.dp)
                ) {
                    Text(
                        "Welcome"
                    )
                    Text("Name: ")
                }

            }
            Spacer(modifier = Modifier.height(20.dp))

            Card(modifier = Modifier
                .height(200.dp)
                .width(500.dp)
                .border(1.dp, MaterialTheme.colors.onSecondary)
                .padding(top = 100.dp),
                elevation = 10.dp) {
                Column(
//                    your card content here
                    modifier = Modifier.padding(0.dp)
                ) {
                    Text(
                        "Current Month"
                    )
                    Text("February: ")
                    Text("Spent: 90€")
                    Text("See details")
                }
            }
        }
//        Button(onClick = { viewModel.signOut() }) {
//            Text(text = "Log out")
//        }
//        LogoutButton(viewModel)
    }
//    LogoutButton(viewModel)
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

@Composable
fun CreateButton(buttonWidth: Dp, CreateWeeklyListClick: () -> Unit) {
    OutlinedButton(
        onClick = { CreateWeeklyListClick() },
        modifier = Modifier.width(buttonWidth),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(com.example.todolist_app_project.R.color.black),
            contentColor = colorResource(com.example.todolist_app_project.R.color.white)
        )
    )
    {
        Text("Create")
    }
}
