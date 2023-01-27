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
    NewEntryPage(
        title = R.string.new_entry
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
                NewEntryPage.name -> NewEntryPage
                ManualEntry.name -> ManualEntry
                else -> Login // Redirects to Login if some other page, but not logged in
            }
        }

        /*fun toRoute(route: String?, isLoggedIn: State<Boolean>) {
            when (route?.substringBefore("/")) {
                CreateWeeklyList.name -> CreateWeeklyList
            }
        }*/
    }
}