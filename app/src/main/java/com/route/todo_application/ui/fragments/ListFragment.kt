package com.route.todo_application.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.route.todo_application.adapter.TodoAdapter
import com.route.todo_application.database.MyDatabase
import com.route.todo_application.database.model.Todo
import com.route.todo_application.databinding.FragmentTasksBinding

class ListFragment : Fragment() {
    lateinit var todoAdapter : TodoAdapter
    lateinit var binding : FragmentTasksBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTasksBinding.inflate(layoutInflater,container,false)
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

    private fun refreshAdapter(){
        val myList = MyDatabase.getInstance(requireContext()).getTodoDao().getAll()
        todoAdapter.updateNewList(myList)
    }
}