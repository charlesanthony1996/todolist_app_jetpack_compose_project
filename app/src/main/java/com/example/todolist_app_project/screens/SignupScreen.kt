package com.example.todolist_app_project.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.todolist_app_project.LoginViewModel
import com.example.todolist_app_project.R


@Composable
fun SignupScreen(viewModel: LoginViewModel) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (viewModel.error.value.isNotBlank()) {
            ErrorField(viewModel)
        }

//        add composable functions here
        NameFieldElement(viewModel)
        EmailFieldElement(viewModel)
        PasswordFieldElement(viewModel)
        CreateSignupButton(viewModel)


    }

}


@Composable
fun NameFieldElement(viewModel: LoginViewModel) {
    val userPassword = viewModel.name.value

    Text("Username")
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = userPassword,
        label = { Text(text = stringResource(R.string.name)) },
        onValueChange = { viewModel.setUserName(it) }
    )
}

@Composable
fun EmailFieldElement(viewModel: LoginViewModel) {
    val userEmail = viewModel.userEmail.value

    Text("Email")
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = userEmail,
        label = { Text(text = stringResource(R.string.email)) },
        onValueChange = { viewModel.setUserEmail(it) }
    )
}

@Composable
fun PasswordFieldElement(viewModel: LoginViewModel) {
    val password = viewModel.password.value

    Text("Password")
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = PasswordVisualTransformation(),
        value = password,
        label = { Text(text = stringResource(R.string.password)) },
        onValueChange = { viewModel.setPassword(it) }
    )
}

@Composable
fun CreateSignupButton(viewModel: LoginViewModel) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        enabled = viewModel.isValidEmailAndPassword(),
        content = { Text(text = stringResource(R.string.create_account)) },
        onClick = { viewModel.createUserWithEmailAndPassword() }
    )
}

