package com.example.todolist_app_project

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todolist_app_project.screens.*
import com.example.todolist_app_project.ui.theme.NavigationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseScreen()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun BaseScreen(loginViewModel: LoginViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val backstackEntry = navController.currentBackStackEntryAsState()

    val currentScreen = NavigationEnum.fromRoute(
        backstackEntry.value?.destination?.route,
        loginViewModel.isLoggedIn
    )
    NavigationTheme {

        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()

        Column(modifier = Modifier.padding(top = 0.dp), verticalArrangement = Arrangement.Center) {
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    TopAppBar(
                        title = { Text(text = stringResource(currentScreen.title)) },
                        navigationIcon = {
                            if (currentScreen != NavigationEnum.Home && currentScreen != NavigationEnum.Landing) {
                                Button(
                                    onClick = { navController.popBackStack() },
                                    contentPadding = PaddingValues(start = 0.dp, end = 0.dp)
                                ) {
                                    Icon(Icons.Filled.ArrowBack, stringResource(R.string.back_icon))
                                }
                            } else if (currentScreen == NavigationEnum.Home) {
                                Button(
                                    onClick = {},
                                    contentPadding = PaddingValues(start = 0.dp, end = 0.dp)
                                ) {
                                    Icon(
                                        Icons.Filled.Menu,
                                        contentDescription = "Menu",
                                        modifier = Modifier
                                            .size(ButtonDefaults.IconSize)
                                            .clickable(onClick = {
                                                scope.launch {
                                                    scaffoldState.drawerState.apply {
                                                        if (isClosed) open() else close()
                                                    }
                                                }
                                            })
                                    )
                                }
                            }
                        }
                    )
                },
                drawerContent = {
                    Column {
                        Card(modifier = Modifier.fillMaxWidth())
                        {
                            Icon(
                                Icons.Outlined.AccountCircle,
                                contentDescription = "User profile picture",
                                modifier = Modifier.size(50.dp)
                            )
                        }
                        Divider()
                        Spacer(modifier = Modifier.fillMaxWidth())
                        Button(
                            onClick = {},
                            contentPadding = PaddingValues(
                                start = 0.dp, end = 0.dp
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Profile")
                        }
                        Divider()
                        Button(
                            onClick = {},
                            contentPadding = PaddingValues(
                                start = 0.dp, end = 0.dp
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("My items")
                        }
                        Divider()
                        Button(
                            onClick = {},
                            contentPadding = PaddingValues(
                                start = 0.dp, end = 0.dp
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Overview")
                        }
                        Divider()
                        Button(
                            onClick = {},
                            contentPadding = PaddingValues(
                                start = 0.dp, end = 0.dp
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("External links")
                        }
                        Divider()
                        Button(
                            onClick = {},
                            contentPadding = PaddingValues(
                                start = 0.dp, end = 0.dp
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Configuration")
                        }
                        Divider()
                        Button(
                            onClick = {},
                            contentPadding = PaddingValues(
                                start = 0.dp, end = 0.dp
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("About")
                        }
                        Divider()
                        Button(
                            onClick = {
                                scope.launch {
                                    scaffoldState.drawerState.apply {
                                        close()
                                    }
                                }
                                loginViewModel.signOut()
                            },
                            contentPadding = PaddingValues(
                                start = 0.dp, end = 0.dp
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) { Text("Logout") }
                    }
                },
            ) {
                NavigateBetweenScreen(navController)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun NavigateBetweenScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val startDestination =
        if (loginViewModel.isLoggedIn.value) NavigationEnum.Home.name else NavigationEnum.Landing.name

    NavHost(navController = navController, startDestination = startDestination) {
        // TODO viewModels() di doesn't work inside this anymore, every page creates with it own lifecycle
        landingPage(this, navController, loginViewModel)
        loginPage(this, navController, loginViewModel)
//        emailLoginPage(this, loginViewModel)
        signupPage(this, loginViewModel)
        homePage(this, navController, loginViewModel)
        createWeeklyListPage(this, navController, loginViewModel)
        createManualEntryPage(this, loginViewModel)
    }
}

fun landingPage(
    builder: NavGraphBuilder,
    navController: NavHostController,
    loginViewModel: LoginViewModel
) {
    builder.composable(route = NavigationEnum.Landing.name) {
        LandingScreen(
            LoginButtonClick = { navController.navigate(NavigationEnum.Login.name) },
            GetStartedButtonClick = { navController.navigate(NavigationEnum.Signup.name) },
            viewModel = loginViewModel
        )
    }
}

fun loginPage(
    builder: NavGraphBuilder,
    navController: NavHostController,
    loginViewModel: LoginViewModel
) {
    builder.composable(route = NavigationEnum.Login.name) {
        loginViewModel.setError("")
        LoginScreen(
//            emailLoginClick = { navController.navigate(NavigationEnum.EmailLogin.name) },
//            guestLoginClick = { navController.navigate(NavigationEnum.EmailLogin.name) },
            viewModel = loginViewModel
        )
    }
}

/*fun emailLoginPage(builder: NavGraphBuilder, loginViewModel: LoginViewModel) {
    builder.composable(route = NavigationEnum.EmailLogin.name) {
        loginViewModel.setError("")
        EmailLoginScreen(loginViewModel)
    }
}*/

fun signupPage(builder: NavGraphBuilder, loginViewModel: LoginViewModel) {
    builder.composable(route = NavigationEnum.Signup.name) {
        SignupScreen(loginViewModel)
    }
}

@RequiresApi(Build.VERSION_CODES.N)
fun homePage(
    builder: NavGraphBuilder,
    navController: NavHostController,
    loginViewModel: LoginViewModel
) {
    builder.composable(route = NavigationEnum.Home.name) {
        HomeScreen(
            CreateWeeklyListScreenClick = { navController.navigate(NavigationEnum.CreateWeeklyList.name) },
            loginViewModel
        )
    }
}

fun createWeeklyListPage(
    builder: NavGraphBuilder,
    navController: NavHostController,
    loginViewModel: LoginViewModel
) {
    builder.composable(route = NavigationEnum.CreateWeeklyList.name) {
        CreateWeeklyListScreen(
            CreateManualEntryClick = { navController.navigate(NavigationEnum.ManualEntry.name) },
            viewModel = loginViewModel
        )
    }
}

fun createManualEntryPage(
    builder: NavGraphBuilder,
    loginViewModel: LoginViewModel
) {
    builder.composable(route = NavigationEnum.ManualEntry.name) {
        CreateManualEntryScreen(
            loginViewModel
        )
    }
}



