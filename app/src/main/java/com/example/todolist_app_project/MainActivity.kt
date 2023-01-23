package com.example.todolist_app_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
//import androidx.compose.material.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.todolist_app_project.NavigationEnum
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todolist_app_project.screens.EmailLoginScreen
import com.example.todolist_app_project.screens.LoginScreen
import com.example.todolist_app_project.screens.WelcomeScreen
import com.example.todolist_app_project.ui.theme.NavigationTheme
import com.example.todolist_app_project.R
import com.example.todolist_app_project.screens.CreateWeeklyListScreen
import kotlin.math.log

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseScreen()
        }
    }
}

@Composable
fun BaseScreen(loginViewModel: LoginViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val backstackEntry = navController.currentBackStackEntryAsState()

    val currentScreen = NavigationEnum.fromRoute(backstackEntry.value?.destination?.route, loginViewModel.isLoggedIn)
    NavigationTheme {
        Scaffold(
            topBar = { TopBar(navController, currentScreen) }
        ) {
            NavigateBetweenScreen(navController)
        }
    }
}

@Composable
fun TopBar(navController: NavHostController, currentScreen: NavigationEnum) {
    TopAppBar(
        title = { Text(text = stringResource(currentScreen.title)) },
        // To avoid going back to previous screen after login/logout click
        navigationIcon = {
            if (currentScreen != NavigationEnum.Welcome
                && currentScreen != NavigationEnum.Login
            ) {
                NavigateBackButton(navController)
            }
        }
    )
}

@Composable
fun NavigateBackButton(navController: NavHostController) {
    IconButton(onClick = { navController.popBackStack() },
        modifier = Modifier.semantics { contentDescription = "back button" }) {

        Icon(Icons.Filled.ArrowBack, stringResource(R.string.back_icon))
    }
}

@Composable
fun NavigateBetweenScreen(navController: NavHostController, loginViewModel: LoginViewModel = hiltViewModel()) {
    val startDestination = if (loginViewModel.isLoggedIn.value) NavigationEnum.Welcome.name else NavigationEnum.Login.name

    NavHost(navController = navController, startDestination = startDestination) {
        // TODO viewModels() di doesn't work inside this anymore, every page creates with it own lifecycle
        loginPage(this, navController, loginViewModel)
        emailLoginPage(this, loginViewModel)
        welcomePage(this, navController, loginViewModel)
        createWeeklyListPage(this,navController, loginViewModel)
    }
}

fun loginPage(builder: NavGraphBuilder, navController: NavHostController, loginViewModel: LoginViewModel) {
    builder.composable(route = NavigationEnum.Login.name) {
        loginViewModel.setError("")
        LoginScreen(
            emailLoginClick = { navController.navigate(NavigationEnum.EmailLogin.name) },
            guestLoginClick = {navController.navigate(NavigationEnum.EmailLogin.name)},
            viewModel = loginViewModel
        )
    }
}

fun emailLoginPage(builder: NavGraphBuilder, loginViewModel: LoginViewModel) {
    builder.composable(route = NavigationEnum.EmailLogin.name) {
        loginViewModel.setError("")
        EmailLoginScreen(loginViewModel)
    }
}

fun welcomePage(builder: NavGraphBuilder, navController: NavHostController, loginViewModel: LoginViewModel) {
    builder.composable(route = NavigationEnum.Welcome.name) {
        WelcomeScreen(
            CreateWeeklyListScreenClick = { navController.navigate(NavigationEnum.CreateWeeklyList.name)},
            loginViewModel
        )
    }
}

fun createWeeklyListPage(builder: NavGraphBuilder, navController: NavHostController, loginViewModel: LoginViewModel) {
    builder.composable(route = NavigationEnum.CreateWeeklyList.name) {
        CreateWeeklyListScreen(loginViewModel)
    }
}
