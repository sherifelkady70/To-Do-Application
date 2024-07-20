package com.route.todo_application.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.route.todo_application.clearTime
import com.route.todo_application.data.database.MyDatabase
import com.route.todo_application.data.database.model.Todo
import com.route.todo_application.databinding.FragmentAddTaskBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class AddTaskFragment(private val onAddClick : () -> Unit) : BottomSheetDialogFragment() {
    lateinit var binding : FragmentAddTaskBinding
    private var selectedDateForDatabase: Calendar = Calendar.getInstance()
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
    }

    private fun fabAddTask(){
        binding.addTaskBtn.setOnClickListener {
            if(validateTexts()){
                val title = binding.title.text.toString()
                val description = binding.description.text.toString()
                selectedDateForDatabase.clearTime()
                val todo = Todo(
                    title = title, description = description, isDone = false,
                    date = selectedDateForDatabase.timeInMillis)
                MyDatabase.getInstance(requireActivity().applicationContext).getTodoDao().insert(todo)
                dismiss()
                Log.e("add","selectedDate in inserted add Task and after clearTime() and millis : ${selectedDateForDatabase.timeInMillis}")
                onAddClick.invoke()
            }
        }
    }

    private fun handleDate(){
        binding.selectDateTv.text = "${selectedDateForDatabase.get(Calendar.DAY_OF_MONTH)} /" +
                "${selectedDateForDatabase.get(Calendar.MONTH) + 1} /" +
                "${selectedDateForDatabase.get(Calendar.YEAR)}"
        binding.selectDateTv.setOnClickListener {
            val datePicker = DatePickerDialog(requireContext(),
                { _, year, month, dayOfMonth ->
                    selectedDateForDatabase.set(Calendar.YEAR,year)
                    selectedDateForDatabase.set(Calendar.MONTH,month)
                    selectedDateForDatabase.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                    binding.selectDateTv.text = "${dayOfMonth}/${month+1}/${year}"
                }, selectedDateForDatabase.get(Calendar.DAY_OF_MONTH),selectedDateForDatabase.get(Calendar.MONTH)+1
                ,selectedDateForDatabase.get(Calendar.YEAR))
            datePicker.datePicker.minDate = Calendar.getInstance().timeInMillis
            datePicker.show()
        }
        Log.e("add","selectedDate of add Task before clearTime(): ${selectedDateForDatabase}")
        fabAddTask()
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

