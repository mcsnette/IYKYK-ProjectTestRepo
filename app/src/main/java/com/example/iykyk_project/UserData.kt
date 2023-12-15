package com.example.iykyk_project

data class UserData(
    val id: String,
    val email: String,
    val username: String,
    val password: String,
    val fullname: String = ""
)
