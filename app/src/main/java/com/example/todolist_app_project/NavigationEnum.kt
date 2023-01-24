package com.example.todolist_app_project

import androidx.compose.runtime.State

enum class NavigationEnum (val title: Int) {
    Landing (
        title = R.string.landing
    ),
    Login(
        title = R.string.login
    ),
    Signup(
        title = R.string.signup
    ),
    EmailLogin(
        title = R.string.email
    ),
    Home(
        title = R.string.home
    ),
    CreateWeeklyList(
        title = R.string.create_weekly_list
    ),
    ManualEntry(
        title = R.string.create_manual_entry
    );

    companion object {
        fun fromRoute(route: String?, isLoggedIn: State<Boolean>): NavigationEnum {

            return when (route?.substringBefore("/")) {
                Login.name -> Login
                Landing.name -> Landing
                Signup.name -> Signup
                EmailLogin.name -> EmailLogin
                Home.name -> Home
                CreateWeeklyList.name -> CreateWeeklyList
                ManualEntry.name -> ManualEntry
                else -> Login // Redirects to Login if some other page, but not logged in
            }
            /*return if (!isLoggedIn.value) {
                when (route?.substringBefore("/")) {
                    Login.name -> Login
                    Landing.name -> Landing
                    Signup.name -> Signup
                    EmailLogin.name -> EmailLogin
                    Home.name -> Home
                    CreateWeeklyList.name -> CreateWeeklyList
                    else -> Login // Redirects to Login if some other page, but not logged in
                }
            } else {
                // Define here all your logged in routes
                when (route?.substringBefore("/")) {
                    Landing.name -> Home
                    Login.name -> Home
                    Signup.name -> Home
                    EmailLogin.name -> Home
                    CreateWeeklyList.name -> Home
                    Home.name -> Home

                    null -> Home

                    else -> throw IllegalArgumentException("Route $route is not recognized.")
                }
            }*/
        }

        fun toRoute(route: String?, isLoggedIn: State<Boolean>) {
            when (route?.substringBefore("/")) {
                CreateWeeklyList.name -> CreateWeeklyList
            }
        }
    }
}