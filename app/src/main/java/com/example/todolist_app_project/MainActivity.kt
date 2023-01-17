package com.example.todolist_app_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
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
                            Text("Shopping list app")
                            Button(onClick={/* */},
                            contentPadding = PaddingValues(
                                start= 20.dp
                            )) {
                                Text("Add item")
                            }

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

