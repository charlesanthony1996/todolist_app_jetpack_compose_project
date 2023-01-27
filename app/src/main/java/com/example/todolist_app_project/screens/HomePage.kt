package com.example.todolist_app_project.screens

import android.content.ContentValues.TAG
import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.todolist_app_project.LoginViewModel
import com.example.todolist_app_project.ui.theme.accentBlue
import com.example.todolist_app_project.ui.theme.lightBlue
import com.example.todolist_app_project.ui.theme.white
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.asDeferred
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.*


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun HomeScreen(NavigateToNewEntryScreen: () -> Unit, viewModel: LoginViewModel) {
    Column(
        modifier = Modifier
            .padding(28.dp),
        verticalArrangement = Arrangement.Center,

        ) {

        UserWelcome(viewModel)

        Spacer(modifier = Modifier.height(20.dp))

        CurrentMonthDisplayCard()

        CreateNewEntryButton(NavigateToNewEntryScreen)
    }
}

@Composable
fun UserWelcome(viewModel: LoginViewModel) {


    Column(
    ) {
        Text(
            text = "Welcome",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = lightBlue
            )
        )

        /*LaunchedEffect(true){
            FirebaseFirestore.getInstance()
                .collection("users")
                .whereEqualTo("email", FirebaseAuth.getInstance().currentUser?.email.toString())
                .get()
                .addOnCompleteListener {results ->
//                    user = results.result.documents.map {  it.data as Map<String, Any> }
                    println( "auth email: " + FirebaseAuth.getInstance().currentUser?.email.toString())
                    for(chap in results.result.documents.map {  it.data as Map<String, Any> }){
                        println("User: " + chap.entries)
                    }
                }
                .addOnFailureListener{
                    print("Failure")
                }
                .await()
        }*/

        val user = remember { mutableStateOf(emptyList<Map<String, Any>>()) }

        LaunchedEffect(true) {
            val result = FirebaseFirestore.getInstance()
                .collection("users")
                .whereEqualTo("email", FirebaseAuth.getInstance().currentUser?.email.toString())
                .get()
                .asDeferred()
                .await()

            user.value = result.documents.map { it.data as Map<String, Any> }

            println(user.value)

        }

        val userName = user.value[0]["name"].toString()
        Text(
            text = userName,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        )

        /*Text(
            text = "userName",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        )*/
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun CurrentMonthDisplayCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        elevation = 10.dp,
        shape = RoundedCornerShape(14.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(brush = Brush.verticalGradient(colors = listOf(white, lightBlue)))
        ) {
            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(bottom = 12.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column() {
                        Text(
                            text = "Current Month",
                            style = TextStyle(
                                fontSize = 16.sp,
                            )
                        )

                        val calendar = Calendar.getInstance()
                        val currentMonth =
                            SimpleDateFormat("MMMM", Locale.getDefault()).format(calendar.time)

                        Text(
                            text = "$currentMonth: ",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = accentBlue
                            )
                        )
                    }
                    Text(
                        text = "See details",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = accentBlue
                        )
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(end = 24.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Spent:",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "90€",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

            }
        }
    }
}

@Composable
fun CreateNewEntryButton(CreateWeeklyListScreenClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FloatingActionButton(
            onClick = { CreateWeeklyListScreenClick() },
            modifier = Modifier.padding(0.dp),
        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = "create a weekly list",
                modifier = Modifier.size(ButtonDefaults.IconSize),
            )
        }
    }
}





















