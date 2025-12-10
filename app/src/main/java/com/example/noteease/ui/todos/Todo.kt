package com.example.noteease.ui.todos

data class TodoItem(
    var id: Int = -1,
    var text: String,
    var isChekced: Boolean = false
)

data class Todo(
    val id: Int =-1,
    var title: String,
    var items: MutableList<TodoItem> = mutableListOf()
)
