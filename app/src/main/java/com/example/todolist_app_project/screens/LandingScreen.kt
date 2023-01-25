package com.example.todolist_app_project.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist_app_project.LoginViewModel
import com.example.todolist_app_project.R
import com.example.todolist_app_project.ui.theme.accentBlue
import com.example.todolist_app_project.ui.theme.lightBlue


@Composable
fun LandingScreen(
    GetStartedButtonClick: () -> Unit,
    LoginButtonClick: () -> Unit,
    viewModel: LoginViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(lightBlue)
            .padding(start = 32.dp, end = 32.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ShowLogo()
        BigWelcome()
        ShowFillerImage()
        GetStartedButton(GetStartedButtonClick)
        LoginButton(LoginButtonClick)
    }
}

@Composable
fun BigWelcome() {
    Text(
        text = "Welcome",
        style = TextStyle(
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold,
        )
    )
    Text(
        text = "“An app to save on your supermarket purchases”",
        style = TextStyle(
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center
        ),
    )
}

@Composable
fun GetStartedButton(GetStartButtonClick: () -> Unit) {
    Button(
        onClick = { GetStartButtonClick() },
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = accentBlue,
            contentColor = Color.White
        )
    ) {
        Text(
            text = "Get Started",
            style = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            ),
        )
    }
}

@Composable
fun LoginButton(LoginButtonClick: () -> Unit) {

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Already have an account?",
            style = TextStyle(
                color = Color.White,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            ),
        )
        Text(
            text = "Login",
            modifier = Modifier.clickable { LoginButtonClick() },
            style = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            ),
        )
    }
}

@Composable
fun ShowLogo() {
    Image(
        painter = painterResource(id = R.drawable.landing_icon),
        contentScale = ContentScale.FillHeight,
        modifier = Modifier
            .padding(top = 12.dp, bottom = 6.dp)
            .height(150.dp),
        contentDescription = "app logo"
    )
}

@Composable
fun ShowFillerImage() {
    Image(
        painter = painterResource(id = R.drawable.filler_img),
        modifier = Modifier
            .width(300.dp)
            .height(300.dp),
        contentDescription = "Landing page image."
    )
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


