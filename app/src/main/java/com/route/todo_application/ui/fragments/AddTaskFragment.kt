package com.route.todo_application.ui.fragments

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.route.todo_application.database.MyDatabase
import com.route.todo_application.database.model.Todo
import com.route.todo_application.databinding.FragmentAddTaskBinding
import java.util.Calendar

class AddTaskFragment(private val onAddClick : () -> Unit) : BottomSheetDialogFragment() {
    lateinit var binding : FragmentAddTaskBinding
    private var selectedDate: Calendar = Calendar.getInstance()
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
        handleDate()
        fabAddTask()
    }

    private fun fabAddTask(){
        binding.addTaskBtn.setOnClickListener {
            if(validateTexts()){
                val title = binding.title.text.toString()
                val description = binding.description.text.toString()
                val todo = Todo(title= title, description = description, isDone = false,
                    date = selectedDate.timeInMillis)
                MyDatabase.getInstance(requireActivity().applicationContext).getTodoDao().insert(todo)
                dismiss()
                onAddClick.invoke()
            }
        }
    }

    private fun handleDate(){
        binding.selectDateTv.text = "${selectedDate.get(Calendar.DAY_OF_MONTH)} /" +
                "${selectedDate.get(Calendar.MONTH)+1} /" +
                "${selectedDate.get(Calendar.YEAR)}"
        val day = selectedDate.get(Calendar.DAY_OF_MONTH)
        val month = selectedDate.get(Calendar.MONTH)+1
        val year = selectedDate.get(Calendar.YEAR)
        binding.selectDateTv.setOnClickListener {
            val datePicker = DatePickerDialog(requireContext(),object : OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    binding.selectDateTv.text = "${dayOfMonth}/${month+1}/${year}"
                }

            }, day,month,year)
            datePicker.show()
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

