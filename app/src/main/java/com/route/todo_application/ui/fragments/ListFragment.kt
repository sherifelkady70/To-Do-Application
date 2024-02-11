package com.route.todo_application.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.route.todo_application.adapter.TodoAdapter
import com.route.todo_application.database.MyDatabase
import com.route.todo_application.database.model.Todo
import com.route.todo_application.databinding.FragmentTasksBinding

class ListFragment : Fragment() {
    private val todoAdapter  = TodoAdapter(listOf())
    lateinit var binding : FragmentTasksBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTasksBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareRV()
    }

    private fun prepareRV(){
        binding.rvTasks.adapter=todoAdapter
        refreshAdapter()
    }

     fun refreshAdapter(){
        val myList = MyDatabase.getInstance(requireActivity().applicationContext).getTodoDao().getAll()
        todoAdapter.updateNewList(myList)
    }
}