package com.example.noteease.ui.todos

object TodosRepository {
    private val todos = mutableListOf<Todo>()
    private var todoIdCounter = 0
    private var itemIdCounter = 0


    fun getAllTodos(): List<Todo> = todos

    fun getTodoById(id: Int): Todo? = todos.find { it.id == id }

    fun addTodo(title: String, items: MutableList<TodoItem>) {
        items.forEach { if (it.id == -1) it.id = itemIdCounter++ }
        val todo = Todo(id = todoIdCounter++, title = title, items = items)
        todos.add(todo)
    }

    fun updateTodo(todoId: Int, newTitle: String, newItems: MutableList<TodoItem>) {
        val todo = todos.find { it.id == todoId } ?: return
        newItems.forEach { if (it.id == -1) it.id = itemIdCounter++ }
        todo.title = newTitle
        todo.items = newItems
    }

    fun deleteTodo(todoId: Int) {
        todos.removeAll { it.id == todoId }
    }
}