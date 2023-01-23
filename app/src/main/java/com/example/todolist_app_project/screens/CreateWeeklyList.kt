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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import androidx.compose.material.ListItem

@Composable
fun CreateWeeklyListScreen(viewModel: LoginViewModel) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()


    Column( modifier = Modifier.padding(0.dp), verticalArrangement = Arrangement.Center) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar {
                    Text("This is it")
                }
            },
            bottomBar = {
                BottomAppBar {
                    Text("This is it")
                }
            },

        ) {
            AddItemForm()


//            firebase addition
//            val itemName = remember { mutableStateOf("") }
//            var itemPrice = remember { mutableStateOf("") }
//
//                TextField(
//                    modifier = Modifier.padding(start = 10.dp),
//                    value = itemName.value,
//                    onValueChange = { itemName.value = it },
//                    label = { Text("Item Name") }
//                )
//            Divider()
//            Spacer (modifier = Modifier.height(10.dp))
//                TextField(
//                    modifier = Modifier.padding(start = 0.dp),
//                    value = itemPrice.value,
//                    onValueChange = { itemPrice.value = it },
//                    label = { Text("Item Price") }
//                )


//            Button(onClick = {
//                val db = FirebaseFirestore.getInstance()
//                db.collection("items")
//                    .add(mapOf("name" to itemName.value, "price" to itemPrice.value.toDouble()))
//                    .addOnSuccessListener {
//                        // Clear the form after successful submission
//                        itemName.value = ""
//                        itemPrice.value = ""
//                    }
//            },
//            modifier = Modifier) {
//                Text("Submit")
//            }

        }
    }
}


@Composable
fun AddItemForm() {
    val itemName = remember { mutableStateOf("") }
    val itemPrice = remember { mutableStateOf("") }

    Row() {
        Spacer(modifier = Modifier.height(10.dp))

        Column() {
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                modifier = Modifier
                    .width(150.dp)
                    .padding(0.dp),
                value = itemName.value,
                onValueChange = { itemName.value = it },
                label = { Text("Item Name") }
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column() {
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                modifier = Modifier.width(140.dp),
                value = itemPrice.value,
                onValueChange = { itemPrice.value = it },
                label = { Text("Item Price") }
            )
        }
        Spacer(modifier = Modifier.width(5.dp))

        Column() {
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                val db = FirebaseFirestore.getInstance()
                db.collection("items")
                    .add(mapOf("item_name" to itemName.value, "item_price" to itemPrice.value))
                    .addOnSuccessListener {
                        // Clear the form after successful submission
                        itemName.value = ""
                        itemPrice.value = ""
                    }
            }) {
                Text("Submit")
            }
        }
    }
}



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemList() {
    val items = remember { mutableStateOf(emptyList<Map<String, Any>>()) }

    FirebaseFirestore.getInstance()
        .collection("items")
        .get()
        .addOnSuccessListener { snapshot ->
            items.value = snapshot.documents.map { it.data as Map<String, Any> }
        }

    Column {
        AddItemForm()
        items.value.forEach { item ->
            ListItem(
                text = { Text("${item["item_name"]} - ${item["item_price"]}")},
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}

@Composable
fun ClearCompleteList() {
    val items = remember { }

}

























