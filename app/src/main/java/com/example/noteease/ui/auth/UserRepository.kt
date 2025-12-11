package com.example.noteease.ui.auth

object UserRepository {
    private val users = mutableListOf<User>()
    var currentUser: User? = null

    fun addUser(user: User) {
        users.add(user)
    }

    fun login(username: String, password: String): Boolean {
        val user = users.find { it.username == username && it.password == password }
        return if (user != null) {
            currentUser = user
            true
        } else false
    }

    fun logout() {
        currentUser = null
    }

    fun getUser(): User? = currentUser
//    fun getCurrentUser(): User? = currentUser
}