package com.example.todolist_app_project

import androidx.compose.runtime.State

enum class NavigationEnum (val title: Int) {
    Home (
        title = R.string.home
            ),
    Login(
        title = R.string.login
    ),
    Signup(
        title = R.string.signup
    ),
    EmailLogin(
        title = R.string.sign_in_with_email
    ),
    Welcome(
        title = R.string.welcome
    ),
    CreateWeeklyList(
        title = R.string.create_weekly_list
    );

    companion object {
        fun fromRoute(route: String?, isLoggedIn: State<Boolean>): NavigationEnum {
            return if (!isLoggedIn.value) {
                when (route?.substringBefore("/")) {
                    Home.name -> Home
                    Login.name -> Login
                    Signup.name -> Signup
                    EmailLogin.name -> EmailLogin
                    CreateWeeklyList.name -> CreateWeeklyList
                    else -> Login // Redirects to Login if some other page, but not logged in
                }
            } else {
                // Define here all your logged in routes
                when (route?.substringBefore("/")) {
                    Welcome.name -> Welcome
                    Login.name -> Welcome
                    EmailLogin.name -> Welcome
                    CreateWeeklyList.name -> CreateWeeklyList
                    null -> Welcome

                    else -> throw IllegalArgumentException("Route $route is not recognized.")
                }
            }
        }

        fun toRoute(route: String?, isLoggedIn: State<Boolean>) {
            when (route?.substringBefore("/")) {
                CreateWeeklyList.name -> CreateWeeklyList
            }
        }
    }
}