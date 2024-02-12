package com.route.todo_application.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import com.route.todo_application.adapter.TodoAdapter
import com.route.todo_application.database.MyDatabase
import com.route.todo_application.database.model.Todo
import com.route.todo_application.databinding.FragmentTasksBinding
import com.route.todo_application.milliSeconds

class ListFragment : Fragment() {
    private val todoAdapter = TodoAdapter(listOf())
    lateinit var binding: FragmentTasksBinding
    var selectedDate = CalendarDay.today()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTasksBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTasks.adapter = todoAdapter
        binding.calendarView.selectedDate = selectedDate
        binding.calendarView.setOnDateChangedListener(object : OnDateSelectedListener {

            override fun onDateSelected(
                widget: MaterialCalendarView,
                date: CalendarDay,
                selected: Boolean,
            ) {
                selectedDate = date
                binding.calendarView.selectedDate = selectedDate
                refreshAdapter()
            }
        })
       refreshAdapter()
    }

    private fun prepareRV() {
        binding.rvTasks.adapter = todoAdapter
      //  refreshAdapter()
    }

    fun refreshAdapter() {
        val myList = MyDatabase.getInstance(requireActivity().applicationContext).getTodoDao()
            .getTodoByDate(selectedDate.milliSeconds())
        Log.e("Get", "${selectedDate.milliSeconds()}")
        todoAdapter.updateNewList(myList)
  }
//    fun refreshAdapter(selectDate : CalendarDay) {
//        val myList = MyDatabase.getInstance(requireActivity().applicationContext).getTodoDao()
//            .getTodoByDate(selectDate.milliSeconds())
//        Log.e("Get", "${selectDate.milliSeconds()}")
//        todoAdapter.updateNewList(myList)
//    }


//    fun listenerForImage(){
//        todoAdapter.onImageClick = object : TodoAdapter.OnImageClickListener{
//            override fun onImageClick(position: Int) {
//                val todo = Todo(title = todoAdapter.todoList[position].title
//                    , description = todoAdapter.todoList[position].description,
//                date = todoAdapter.todoList[position].date,
//                isDone = true)
//                MyDatabase.getInstance(requireContext().applicationContext).getTodoDao()
//                    .udpate(todo)
//                refreshAdapter()
//            }
//
//        }
//    }
}