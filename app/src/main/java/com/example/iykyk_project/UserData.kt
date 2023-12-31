package com.example.iykyk_project

data class UserData(
    var id: String? = null,
    var email: String? = null,
    var username: String? = null,
    var password: String? = null,
    var fullname: String? = ""
) {
    constructor(username: String?) : this() {
        this.username = username
    }
}