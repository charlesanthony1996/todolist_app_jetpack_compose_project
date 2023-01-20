package com.example.todolist_app_project.screens

import android.hardware.camera2.params.BlackLevelPattern
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
//import androidx.room.*
import com.example.todolist_app_project.ui.theme.NavigationTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationTheme() {

                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()

                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        TopAppBar {
                            Button(
                                onClick= {/* */},
                                contentPadding = PaddingValues(
                                    start= 0.dp
                                )
                            ) {
                                Icon(
                                    Icons.Filled.Menu,
                                    contentDescription = "Menu",
                                    modifier = Modifier.clickable(onClick={
                                        scope.launch {
                                            scaffoldState.drawerState.apply {
                                                if (isClosed) open() else close()
                                            }
                                        }

                                    })
                                )

                            }
                            Spacer(modifier = Modifier.width(180.dp))
                            Button(
                                onClick = {/* */ },
                                contentPadding = PaddingValues(
                                    start = 20.dp
                                )
                            ) {
                                Text("Favourites")
                            }
                            Spacer(modifier = Modifier.width(0.dp))
                            Button(
                                onClick = {},
                                contentPadding = PaddingValues(
                                    start = 0.dp, end = 0.dp
                                )
                            ) {
                                Icon(
                                    Icons.Filled.Person,
                                    contentDescription = "Settings",
                                    modifier = Modifier.size(ButtonDefaults.IconSize)
                                )
                            }
                        }
                    },



                    bottomBar = {
                        BottomAppBar {
                            Spacer(modifier = Modifier.width(10.dp))
                            Icon(
                                Icons.Filled.Edit,
                                contentDescription = "create a weekly list",
                                modifier=Modifier.size(30.dp)
                            )
                            Spacer(modifier= Modifier.width(130.dp))
                            Button(
                                onClick= {/**/},
                                contentPadding = PaddingValues(
                                    start = 0.dp, end = 0.dp,
                                ),
                                shape= MaterialTheme.shapes.large,
                            ) {
                                Icon(
                                    Icons.Filled.Add,
                                    contentDescription = "Add a list nigga!",
                                    modifier = Modifier.size(40.dp),
                                )
                            }
                        }
                    },

                    drawerContent = {
                        Column {
                            Card(modifier = Modifier.fillMaxWidth(),

                                ) {
                                Icon(
                                    Icons.Outlined.AccountCircle,
                                    contentDescription = "User profile picture",
                                    modifier = Modifier.size(50.dp)
                                )
                            }
                            Divider()
                            Spacer(modifier = Modifier.fillMaxWidth())
                            Button(
                                onClick = {/**/ },
                                contentPadding = PaddingValues(
                                    start = 0.dp, end = 0.dp
                                ),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Yearly Balance")
                            }
                            Divider()
                            Button(
                                onClick = {/**/ },
                                contentPadding = PaddingValues(
                                    start = 0.dp, end = 0.dp
                                ),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Goals")
                            }
                            Divider()
                            Button(
                                onClick = {/**/ },
                                contentPadding = PaddingValues(
                                    start = 0.dp, end = 0.dp
                                ),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("External links")
                            }
                            Divider()
                            Button(
                                onClick = {/**/ },
                                contentPadding = PaddingValues(
                                    start = 0.dp, end = 0.dp
                                ),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Configuration")
                            }
                            Divider()
                        }
                    }
                )
                {
                    // Screen content
//                    Welcome and name
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.height(2.dp))
                        Text("Welcome")
                        Spacer(modifier = Modifier.height(5.dp))
                        Text("Name: ")
                        Spacer(modifier = Modifier.height(10.dp))

                        Card(
                            modifier = Modifier.width(200.dp)
                        ) {
                            Column (
                                modifier = Modifier.width(200.dp),
//                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Text("Current Month")
                            }
                        }
                    }
                }
            }
        }
    }
}