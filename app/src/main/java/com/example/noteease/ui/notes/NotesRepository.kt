package com.example.noteease.ui.notes

object NotesRepository {
    private val notes = mutableListOf<Note>()

    fun getAllNotes(): List<Note> {
        return notes
    }

    fun getNoteById(id: Int): Note? {
        return notes.find { it.id == id }
    }

    fun addNote(note: Note) {
        notes.add(note)
    }

    fun updateNote(updated: Note) {
        val index = notes.indexOfFirst { it.id == updated.id }
        if (index != -1) {
            notes[index] = updated
        }
    }

    fun deleteNote(id: Int) {
        notes.removeAll { it.id == id }
    }
}
