package com.route.todo_application.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.prolificinteractive.materialcalendarview.CalendarDay
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
        handleCardViewWithListener()
        prepareRV()
        createIntentForEditTask()
        listenerForDelete()
        listenerForImageDone()
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
        Log.e("add", "selectedDate in database:  ${selectedDate.milliSeconds()}")
        todoAdapter.updateNewList(myList)
  }
    private fun createIntentForEditTask(){
        todoAdapter.onEditClick = object : TodoAdapter.OnEditClickListener{
            override fun onEditItemClick(data: Todo, position: Int) {
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
        todoAdapter.onDeleteItem = object : TodoAdapter.OnItemDeleteListener{
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
    private fun handleCardViewWithListener(){
        binding.calendarView.selectedDate = selectedDate
        binding.calendarView.setOnDateChangedListener { widget, date, selected ->
            selectedDate = date
            prepareRV()
        }
    }

    fun listenerForImageDone(){
        todoAdapter.onImageDoneClick = object : TodoAdapter.OnImageDoneClickListener{
            override fun onImageDoneClick(data: Todo, position: Int) {
                val todo = Todo(
                    id = data.id,
                    title = data.title,
                    description = data.description,
                    date = data.date,
                    isDone = true
                )
                MyDatabase.getInstance(requireActivity()).getTodoDao().udpate(todo)
                Log.e("onBindViewHolder","the isDone after updated in database : ${todo.isDone}")
            }

        }
    }
}