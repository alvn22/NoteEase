package com.example.noteease.ui.todos

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.noteease.R

class ChecklistAdapter(
    private val items: MutableList<TodoItem>,
    private val onDelete: (itemId: Int) -> Unit
) : RecyclerView.Adapter<ChecklistAdapter.VH>() {

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView){
        val checkBox: CheckBox = itemView.findViewById(R.id.cbCheck)
        val todoText: EditText = itemView.findViewById(R.id.etTodoText)
        val btnDelete: ImageButton = itemView.findViewById(R.id.btnDeleteItemTodo)

        fun bind(item: TodoItem) {
            checkBox.isChecked = item.isChekced
            todoText.setText(item.text)

            checkBox.setOnCheckedChangeListener { _, checked ->
                item.isChekced = checked
            }

            todoText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    item.text = s.toString()
                }
            })

            btnDelete.setOnClickListener {
                onDelete(item.id)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_checklist_row, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(
        holder: VH,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

//    fun addItem(item: ChecklistItem) {
//        items.add(item)
//        notifyItemInserted(items.size - 1)
//    }
}