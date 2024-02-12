package com.route.todo_application.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.route.todo_application.R
import com.route.todo_application.databinding.ActivityMainBinding
import com.route.todo_application.ui.fragments.AddTaskFragment
import com.route.todo_application.ui.fragments.SettingsFragment
import java.util.Calendar

class HomeActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private val listFragment = com.route.todo_application.ui.fragments.ListFragment()
    private val settingsFragment = SettingsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragments(listFragment)
        bottomNavView()
    }

    private fun bottomNavView(){
        binding.fabAddTask.setOnClickListener {
            val addTaskFragment = AddTaskFragment{
                listFragment.refreshAdapter()
            }
           addTaskFragment.show(supportFragmentManager," ")
        }
       binding.bottomNavView.setOnItemSelectedListener {
           if(it.itemId == R.id.tasks){
               replaceFragments(listFragment)
           }
           else{
               replaceFragments(settingsFragment)
           }
           Log.d("bottomNavView","${R.id.tasks}")
           return@setOnItemSelectedListener true
       }
   }
    private fun replaceFragments(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .commit()
    }
}