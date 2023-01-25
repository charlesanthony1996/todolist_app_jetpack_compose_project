package com.example.todolist_app_project.screens

import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist_app_project.LoginViewModel
import java.text.SimpleDateFormat
import java.util.*


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun HomeScreen(CreateWeeklyListScreenClick: () -> Unit, viewModel: LoginViewModel) {
    Column(modifier = Modifier.padding(top = 0.dp), verticalArrangement = Arrangement.Center) {
        Scaffold(
            bottomBar = {
                BottomAppBar {
                    val buttonWidth = 100.dp
                    CreateButton(buttonWidth, CreateWeeklyListScreenClick)
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {},
                    modifier = Modifier.padding(0.dp),
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
            Card(
                modifier = Modifier
                    .height(100.dp)
                    .width(500.dp)
                    .border(1.dp, MaterialTheme.colors.onSecondary)
                    .padding(0.dp),
                elevation = 10.dp
            ) {
                Column(
                    modifier = Modifier.padding(0.dp)
                ) {
                    Text(
                        "Welcome", fontSize = 30.sp
                    )
                    val userName = viewModel.name.value
                    Text(userName.toString(), fontSize = 20.sp)
                }

            }
            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier
                    .height(200.dp)
                    .width(500.dp)
                    .border(1.dp, MaterialTheme.colors.onSecondary)
                    .padding(top = 100.dp),
                elevation = 10.dp
            ) {
                Column(
                    modifier = Modifier.padding(0.dp)
                ) {
                    Text("Current Month")
                    val calendar = Calendar.getInstance()
                    val currentMonth = SimpleDateFormat("MMMM", Locale.getDefault()).format(calendar.time)
                    Text("$currentMonth: ")
                    Text("Spent: 90€")
                    Text("See details")
                }
            }
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

//@Composable
//fun CaptureProduct() {
//    val state = rememberSavedInstanceState { CameraState()}
//    Column(
//
//    ) {
//
//    }
//}





















