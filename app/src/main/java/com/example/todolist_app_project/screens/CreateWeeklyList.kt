package com.example.todolist_app_project.screens

import android.os.Build.VERSION_CODES.R
import android.util.Log
import androidx.compose.foundation.*
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.google.common.base.Functions.compose
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase


@Composable
fun CreateWeeklyListScreen(viewModel: LoginViewModel,CreateManualEntryClick: () -> Unit) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()


    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 24.dp)
            .fillMaxSize(),
    )
    {
        AddItemForm()
        ItemList()
//        ShowTotalField()
        ClearCompleteList()
        CreateManualEntryButton(CreateManualEntryClick)
    }
//        Scaffold(
//            scaffoldState = scaffoldState,
//            topBar = {
//                TopAppBar {
//                    Text("This is it")
//                }
//            },
//            bottomBar = {
//                BottomAppBar {
//                    Text("This is it")
//                }
//            },
//
//        )
//        {
//            AddItemForm()
//            Row {
//                ClearCompleteList()
//            }
//            ItemList()
//            ClearCompleteList()
//            ShowItemsByLoggedInUser()
//            Column(modifier = Modifier.padding(10.dp)) {
//                Row{
//                    ClearCompleteList()
//                }
//            }


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

//        }
//    }
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
            },
                modifier = Modifier.width(60.dp)
            ) {
//                Text("Submit", fontSize = 10.sp)
                Icon(
                    Icons.Filled.Check,
                    contentDescription = "Check item into db",

                )
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
//        AddItemForm()
        items.value.forEach { item ->
            ListItem(
                text = { Text("${item["item_name"]} - ${item["item_price"]}",
                    fontSize = 25.sp)},
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShowTotalField() {
    val totalPrice = remember { mutableStateOf(0.0) }

    FirebaseFirestore.getInstance()
        .collection("items")
        .get()
        .addOnSuccessListener { snapshot ->
            totalPrice.value = snapshot.documents.sumOf {
                it.getDouble("item_price") ?: 0.0
            }
        }

    Text("Total Price: $totalPrice", fontSize = 20.sp)

}

@Composable
fun ClearCompleteList() {
    val db = FirebaseFirestore.getInstance()
    val itemsRef = db.collection("items")

    Button(onClick = {
        itemsRef.get().addOnSuccessListener { snapshot ->
            for (document in snapshot.documents) {
                document.reference.delete()
            }
        }
    })
    {
        Text("Delete list")
    }
}


@Composable
fun ShowItemsByLoggedInUser() {
    val user = FirebaseAuth.getInstance().currentUser
    val itemsRef = FirebaseFirestore.getInstance().collection("items")
    val query = itemsRef.whereEqualTo("userId", user?.uid)

    query.get().addOnSuccessListener { querySnapshot ->
        for (doc in querySnapshot) {
//            println("${doc.id} => ${doc.data}")
            Log.d("MyTag", "${doc.id} => ${doc.data}")
        }
    }


}


@Composable
fun CreateManualEntryButton(CreateManualEntryClick: () -> Unit) {
    Button(
        onClick = { CreateManualEntryClick() },
        modifier = Modifier
            .width(200.dp)
            .height(50.dp),
        content ={ Text("Create Manual Entry")}
    )
}






















