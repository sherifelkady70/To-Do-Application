package com.route.todo_application.ui.fragments

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.route.todo_application.databinding.FragmentAddTaskBinding
import java.util.Calendar

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
        handleDate()
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

    private fun handleDate(){
        val selectedDate = Calendar.getInstance()
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


}

