package com.example.todolist_app_project.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolist_app_project.LoginViewModel


@Composable
fun LandingScreen(
    GetStartedButtonClick: () -> Unit,
    LoginButtonClick: () -> Unit,
    viewModel: LoginViewModel
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(start = 24.dp, top = 500.dp, end = 24.dp, bottom = 24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        GetStartedButton(GetStartedButtonClick)
        LoginButton(LoginButtonClick)
    }

}

@Composable
fun GetStartedButton(GetStartButtonClick:() -> Unit) {
    Button(
        onClick = { GetStartButtonClick() },
        modifier = Modifier
            .width(150.dp)
            .height(50.dp),
    ) {
        Text("Get Started")
    }
}

@Composable
fun LoginButton(LoginButtonClick: () -> Unit) {
    Button(
        onClick = { LoginButtonClick() },
        modifier = Modifier
            .width(150.dp)
            .height(50.dp),
    ) {
        Text("Login")
    }
}


//@Composable
//fun SignInWithEmailButton(buttonWidth: Dp, emailLoginClick: () -> Unit) {
//    OutlinedButton(
//        onClick = { emailLoginClick() },
//        modifier = Modifier.width(buttonWidth),
//        colors = ButtonDefaults.buttonColors(
//            backgroundColor = colorResource(R.color.red),
//            contentColor = colorResource(R.color.white)
//        )
//    ) {
//        SignInButtonRow(iconId = R.drawable.ic_baseline_mail_24, buttonTextId = R.string.sign_in_with_email)
//    }
//}


