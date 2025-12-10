package com.example.noteease.ui.todos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noteease.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TodosDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TodosDetailFragment : Fragment() {

    private var todoId: Int = -1
    private val checklist = mutableListOf<TodoItem>()
    private lateinit var adapter: ChecklistAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todos_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var etTitle = view.findViewById<EditText>(R.id.etTodoTitleDetail)
        var rvChecklist = view.findViewById<RecyclerView>(R.id.rvChecklist)
        var btnAddItem = view.findViewById<Button>(R.id.btnAddItem)
        var btnSave = view.findViewById<Button>(R.id.btnSaveTodos)

        arguments?.let {
            todoId = it.getInt("todoId", -1)
        }

        adapter = ChecklistAdapter(checklist) { itemId ->
            val index = checklist.indexOfFirst { it.id == itemId }
            if (index != -1) {
                checklist.removeAt(index)
                adapter.notifyItemRemoved(index)
            }
        }

        rvChecklist.layoutManager = LinearLayoutManager(requireContext())
        rvChecklist.adapter = adapter

        if (todoId != -1) {
            val todo = TodosRepository.getTodoById(todoId)
            todo?.let {
                etTitle.setText(it.title)
                checklist.addAll(it.items)
                adapter.notifyDataSetChanged()
            }
        }

        btnAddItem.setOnClickListener {
            val newItem = TodoItem(id = -1, text = "")
            checklist.add(newItem)
            adapter.notifyItemInserted(checklist.size - 1)
        }

        btnSave.setOnClickListener {
            val title = etTitle.text.toString()
            if (title.isBlank()) {
                Toast.makeText(requireContext(), "Judul harus diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (todoId == -1) {
                // Create baru
                TodosRepository.addTodo(title, checklist)
            } else {
                // Update existing
                TodosRepository.updateTodo(todoId, title, checklist)
            }

            requireActivity().onBackPressed()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TodosDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TodosDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}