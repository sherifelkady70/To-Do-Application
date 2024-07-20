package com.route.todo_application.ui

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
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
    private var selectedDateForDatabse: Calendar = Calendar.getInstance()
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
                selectedDateForDatabse.clearTime()
                val todo = Todo(
                    title = title, description = description, isDone = false,
                    date = selectedDateForDatabse.timeInMillis)
                MyDatabase.getInstance(requireActivity().applicationContext).getTodoDao().insert(todo)
                dismiss()
                Log.e("add","selectedDate in inserted add Task and after clearTime() and millis : ${selectedDateForDatabse.timeInMillis}")
                onAddClick.invoke()
            }
        }
    }

    private fun handleDate(){
        binding.selectDateTv.text = "${selectedDateForDatabse.get(Calendar.DAY_OF_MONTH)} /" +
                "${selectedDateForDatabse.get(Calendar.MONTH)+1} /" +
                "${selectedDateForDatabse.get(Calendar.YEAR)}"
        binding.selectDateTv.setOnClickListener {
            val datePicker = DatePickerDialog(requireContext(),object : OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    selectedDateForDatabse.set(Calendar.YEAR,year)
                    selectedDateForDatabse.set(Calendar.MONTH,month)
                    selectedDateForDatabse.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                    binding.selectDateTv.text = "${dayOfMonth}/${month+1}/${year}"
                }

            }, selectedDateForDatabse.get(Calendar.DAY_OF_MONTH),selectedDateForDatabse.get(Calendar.MONTH)+1
                ,selectedDateForDatabse.get(Calendar.YEAR))
            datePicker.datePicker.minDate = Calendar.getInstance().timeInMillis
            datePicker.show()
        }
        Log.e("add","selectedDate of add Task before clearTime(): ${selectedDateForDatabse}")
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

