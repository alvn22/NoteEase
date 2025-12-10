package com.example.noteease.ui.todos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.noteease.R

class TodosAdapter(private val todos: MutableList<Todo>,
                   private val onClick: (todo: Todo) -> Unit) : RecyclerView.Adapter<TodosAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.tvTodosTitle)
        val layoutItem = itemView.findViewById<LinearLayout>(R.id.llTodosItemsContainer)
//        val container = itemView.findViewById<CardView>(R.id.cardTodo)
        fun bind(todo: Todo) {
            title.text = todo.title
            layoutItem.removeAllViews()

            for (item in todo.items) {
                val tvItem = TextView(itemView.context).apply {
                    text = if (item.isChekced) "✔ ${item.text}" else "• ${item.text}"
//                    textSize = 14f
//                    setPadding(8, 4, 8, 4)
                }
                layoutItem.addView(tvItem)
            }
            itemView.setOnClickListener {
                onClick(todo)
            }
        }

//        val btnDelete = itemView.findViewById<Button>(R.id.btnDeleteTodo)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todos_card, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: TodoViewHolder,
        position: Int
    ) {
        holder.bind(todos[position])
//        val todo = todos[position]
//        holder.title.text = todo.title
//
//        holder.container.setOnClickListener { onClick(todo) }
    }

    override fun getItemCount() = todos.size
}