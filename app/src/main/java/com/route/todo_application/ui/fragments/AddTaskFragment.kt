package com.route.todo_application.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.route.todo_application.databinding.FragmentAddTaskBinding

class AddTaskFragment : BottomSheetDialogFragment() {
    lateinit var binding : FragmentAddTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTaskBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fabAddTask()
    }

    private fun fabAddTask(){
        binding.addTaskBtn.setOnClickListener {
            if(validateTexts()){
                //code for database
            }
        }
    }

    private fun validateTexts(): Boolean {
        var isValid = true
       val title =  binding.title.editableText.toString()
       val des =  binding.description.editableText.toString()
        if(title.isEmpty()){
            binding.title.error = "Please Enter Title For Todo"
            isValid=false
        }
        if(des.isEmpty()){
            binding.description.error = "Please Enter Description For Todo"
            isValid = false
        }
        return isValid
    }
}

