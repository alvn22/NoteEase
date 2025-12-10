package com.example.noteease.ui.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noteease.R

class NotesAdapter(private val items: MutableList<Note>,
                   private val onClick: (Note) -> Unit,
                   private val onDelete: (Note) -> Unit) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
    inner class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvNotesTitle)
        val tvContent: TextView = view.findViewById(R.id.tvNotesBody)
        val btnDelete: ImageButton = view.findViewById(R.id.btnDeleteNotes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_notes_card, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = items[position]

        holder.tvTitle.text = note.title
        holder.tvContent.text = note.content

        holder.itemView.setOnClickListener {
            onClick(note)
        }
        holder.btnDelete.setOnClickListener { onDelete(note) }
    }

    override fun getItemCount(): Int = items.size

    fun removeNote(note: Note) {
        val index = items.indexOfFirst { it.id == note.id }
        if (index != -1) {
            items.removeAt(index)
            notifyItemRemoved(index)
        }
    }
}