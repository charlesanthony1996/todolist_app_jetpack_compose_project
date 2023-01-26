package com.example.todolist_app_project.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist_app_project.LoginViewModel
import com.example.todolist_app_project.R
import com.example.todolist_app_project.ui.theme.accentBlue
import com.example.todolist_app_project.ui.theme.lightBlue


@Composable
fun SignupScreen(viewModel: LoginViewModel) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(lightBlue)
            .padding(start = 32.dp, end = 32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (viewModel.error.value.isNotBlank()) {
            ErrorField(viewModel)
        }
        Column(
            Modifier.padding(top = 28.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//      add composable functions here
            NameFieldElement(viewModel)
            EmailFieldElement(viewModel)
            PasswordFieldElement(viewModel)
            CreateSignupButton(viewModel)
            OrOtherOptions(viewModel)
        }
    }
}


@Composable
fun NameFieldElement(viewModel: LoginViewModel) {
    val userName = viewModel.name.value

    Text(
        text = "Username",
        style = TextStyle(
            color = Color.White,
            fontSize = 16.sp,
        ),
    )
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White
        ),
        shape = RoundedCornerShape(14.dp),
        value = userName,
        label = { Text(text = stringResource(R.string.name)) },
        onValueChange = { viewModel.setUserName(it) }
    )
}

@Composable
fun EmailFieldElement(viewModel: LoginViewModel) {
    val userEmail = viewModel.userEmail.value

    Text(
        text = "Email",
        style = TextStyle(
            color = Color.White,
            fontSize = 16.sp,
        ),
    )
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White
        ),
        shape = RoundedCornerShape(14.dp),
        value = userEmail,
        label = { Text(text = stringResource(R.string.email)) },
        onValueChange = { viewModel.setUserEmail(it) }
    )
}

@Composable
fun PasswordFieldElement(viewModel: LoginViewModel) {
    val password = viewModel.password.value

    Text(
        text = "Password",
        style = TextStyle(
            color = Color.White,
            fontSize = 16.sp,
        ),
    )
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White
        ),
        shape = RoundedCornerShape(14.dp),
        visualTransformation = PasswordVisualTransformation(),
        value = password,
        label = { Text(text = stringResource(R.string.password)) },
        onValueChange = { viewModel.setPassword(it) }
    )
}

@Composable
fun CreateSignupButton(viewModel: LoginViewModel) {
    Button(
        enabled = viewModel.isValidEmailAndPassword(),
        onClick = { viewModel.createUserWithEmailAndPassword() },
        modifier = Modifier
            .width(200.dp)
            .height(40.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = accentBlue,
            contentColor = Color.White
        )
    ) {
        Text(
            text = "Sign - up",
            style = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            ),
        )
    }
}

