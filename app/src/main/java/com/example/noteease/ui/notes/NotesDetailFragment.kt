package com.example.noteease.ui.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.noteease.R
import com.google.android.material.button.MaterialButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NoteDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NoteDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var noteId = -1
//    private lateinit var inpTitle: EditText
//    private lateinit var inpContent: EditText
//    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noteId = arguments?.getInt("noteId") ?: -1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notes_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var inpTitle = view.findViewById<EditText>(R.id.etNotesTitleDetail)
        var inpContent = view.findViewById<EditText>(R.id.etNotesBodyDetail)
        var btnSave = view.findViewById<Button>(R.id.btnSaveNotes)

        if (noteId != -1) {
            val note = NotesRepository.getNoteById(noteId)
            note?.let {
                inpTitle.setText(note.title)
                inpContent.setText(note.content)
            }
        }

        btnSave.setOnClickListener {
            val title = inpTitle.text.toString().trim()
            val content = inpContent.text.toString().trim()

            if (title.isEmpty()) {
                inpTitle.error = "Judul wajib diisi!"
                return@setOnClickListener
            }

            if (noteId == -1) {
                val newId = (System.currentTimeMillis() % Int.MAX_VALUE).toInt()
                val newNote = Note(newId, title, content)
                NotesRepository.addNote(newNote)
            } else {
                val updated = Note(noteId, title, content)
                NotesRepository.updateNote(updated)
            }

            findNavController().navigate(R.id.action_create_note_to_note_home)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NoteDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NoteDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}