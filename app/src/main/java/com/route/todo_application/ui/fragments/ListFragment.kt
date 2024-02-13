package com.route.todo_application.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import com.route.todo_application.Constant
import com.route.todo_application.adapter.TodoAdapter
import com.route.todo_application.database.MyDatabase
import com.route.todo_application.database.model.Todo
import com.route.todo_application.databinding.FragmentTasksBinding
import com.route.todo_application.milliSeconds
import com.route.todo_application.ui.activities.EditTaskActivity

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
        binding.calendarView.selectedDate = selectedDate
        binding.calendarView.setOnDateChangedListener(object : OnDateSelectedListener {
            override fun onDateSelected(
                widget: MaterialCalendarView,
                date: CalendarDay,
                selected: Boolean,
            ) {
                selectedDate = date
              //  binding.calendarView.selectedDate = selectedDate
                prepareRV()
            }
        })
        prepareRV()
        createIntentForEditTask()
        listenerForDelete()
    }
    override fun onResume() {
        super.onResume()
        refreshAdapter()
        Log.e("onResume","done onresume")
    }
    private fun prepareRV() {
        binding.rvTasks.adapter = todoAdapter
        refreshAdapter()
    }
    fun refreshAdapter() {
        val myList = MyDatabase.getInstance(requireActivity()).getTodoDao()
            .getTodoByDate(selectedDate.milliSeconds())
        Log.e("Get", "${selectedDate.milliSeconds()}")
        todoAdapter.updateNewList(myList)
  }
    private fun createIntentForEditTask(){
        todoAdapter.onWholeItem = object : TodoAdapter.OnWholeItemClickListener{
            override fun onWholeItemClick(data: Todo, position: Int) {
                val intent = Intent(requireActivity(),EditTaskActivity::class.java)
                intent.putExtra(Constant.TITLEKEY,data.title)
                intent.putExtra(Constant.DESKEY,data.description)
                intent.putExtra(Constant.DATEKEY,data.date)
                intent.putExtra(Constant.IDKEY,data.id)
                Log.e("onResume","title : ${ data.title}")
                Log.e("onResume","title : ${ data.description}")
                Log.e("onResume","title : ${ data.date}")
                Log.e("onResume","id : ${data.id}")
                startActivity(intent)
                }

        }
    }
    private fun listenerForDelete(){
        todoAdapter.onDeleteItem = object : TodoAdapter.onItemDeleteListener{
            override fun onDeleteClick(data: Todo, position: Int) {
                val todo = Todo(
                    id=data.id,
                    title =data.title,
                    description = data.description,
                    date = data.date,
                    isDone = false
                )
                Log.e("onClose swipeLayout and test data","id = ${data.id}")
                Log.e("onClose swipeLayout and test data","title = ${data.title}")
                MyDatabase.getInstance(requireActivity()).getTodoDao().delete(todo)
                refreshAdapter()
                Log.e("onClose swipeLayout and test data","refresh fun inside listener: ${refreshAdapter()}")
            }
        }
//        refreshAdapter()
//        Log.e("onClose swipeLayout and test data","refresh fun outside listener: ${refreshAdapter()}")
    }

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