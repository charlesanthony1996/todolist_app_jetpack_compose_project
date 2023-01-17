package com.example.todolist_app_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todolist_app_project.ui.theme.Todolist_app_projectTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Todolist_app_projectTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = {
                        TopAppBar {
                            Icon(
                                Icons.Filled.Menu,
                                contentDescription = "Menu",
                            )
                            Spacer(modifier = Modifier.width(200.dp))
                            Button(
                                onClick = {/* */ },
                                contentPadding = PaddingValues(
                                    start = 20.dp
                                )
                            ) {
                                Text("Favorites")
                            }
                            Spacer(modifier = Modifier.width(30.dp))
                            Button(
                                onClick = {},
                                contentPadding = PaddingValues(
                                    start = 10.dp, end = 10.dp
                                )
                            ) {
                                Icon(
                                    Icons.Filled.Person,
                                    contentDescription = "Settings",
                                    modifier = Modifier.size(ButtonDefaults.IconSize)
                                )
                            }
                        }
                    }
                ) {
                    // Screen content
                }

//               bottom bar init
                Scaffold(
                    bottomBar = {
                        BottomAppBar {
                            Icon(
                                Icons.Filled.Edit,
                                contentDescription = "create the weekly list",
                                modifier=Modifier.size(30.dp)
                            )
                        }
                    }
                ) {
                    // Screen content
                }

            }


        }
    }
}


//creating the database here -> crud
@Entity(tableName="items")
data class Item(
    @PrimaryKey val id: Int,

)

