package com.example.noteease.ui.todos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noteease.R

class TodosAdapter(private val todos: MutableList<Todo>,
                   private val onClick: (todo: Todo) -> Unit,
                   private val onDelete: (todoId: Int) -> Unit) : RecyclerView.Adapter<TodosAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.tvTodosTitle)
        val layoutItem = itemView.findViewById<LinearLayout>(R.id.llTodosItemsContainer)
        val btnDelete = itemView.findViewById<ImageButton>(R.id.btnDeleteTodos)

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
            itemView.setOnClickListener { onClick(todo) }

            btnDelete.setOnClickListener { onDelete(todo.id) }
        }
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
    }

    override fun getItemCount() = todos.size
}