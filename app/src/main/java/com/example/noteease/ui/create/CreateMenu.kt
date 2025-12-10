package com.example.noteease.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.example.noteease.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CreateMenu : BottomSheetDialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.bottom_dialog_create_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = (requireActivity()
            .supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment)
            .navController

        view.findViewById<View>(R.id.btnCreateNote).setOnClickListener {
            navController.navigate(
                R.id.nav_notes_detail,
                Bundle().apply { putInt("noteId", -1) }
            )
            dismiss()
        }
        view.findViewById<View>(R.id.btnCreateTodo).setOnClickListener {
            navController.navigate(
                R.id.nav_todos_detail,
                Bundle().apply { putInt("todoId", -1) }
            )
            dismiss()
        }
    }
}