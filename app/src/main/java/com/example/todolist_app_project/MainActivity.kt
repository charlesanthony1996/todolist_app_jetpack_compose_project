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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.*
import com.example.todolist_app_project.ui.theme.Todolist_app_projectTheme




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {



            Todolist_app_projectTheme {
                // A surface container using the 'background' color from the theme
//                @Composable
//                fun NewView() {
//                    Text("This is the new view")
//                }
//
//                val navController = rememberNavController()
//
//                NavHost(navController = navController, startDestination = "main") {
//                    composable("main") { MainActivity() }
//                    composable("new") { NewView() }
//                }
//
//                Button(onClick = { rememberNavController.navigate("new") }) {
//                    Text("Go to New View")
//                }







                Scaffold(
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
                                    modifier = Modifier.size(30.dp)
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
                            Spacer(modifier= Modifier.width(140.dp))
                            Icon(
                                Icons.Filled.Add,
                                contentDescription = "Add a list nigga!",
                                modifier =Modifier.size(40.dp)
                            )
                        }
                    }



                )



                {
                    // Screen content

                }




            }


        }
    }
}
@Composable
fun DestinationView() {
    Text("This is the categories view nigga!")
}

@Composable
fun MainView() {
    val navController = rememberNavController()
    Column {
        TopAppBar(
            title = { Text("Main View") },
        )
        Button(onClick = { navController.navigate("new") }) {
            Text("Go to New View")
        }
    }
    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainActivity() }
        composable("new") { DestinationView() }
    }
}





//creating the database here -> crud
@Entity(tableName="items")
data class Item(
    @PrimaryKey val id: Int,
    val name:String,
    val price:Double,
    val totalPrice: Double
)

@Dao
interface ItemDao {
    @Insert
    fun insertItem(item: Item)

    @Query("SELECT * FROM items")
    fun getAllItems(): List<Item>

    @Update
    fun updateItem(item: Item)

    @Delete
    fun deleteItem(item: Item)
}


