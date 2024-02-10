package com.route.todo_application.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import com.route.todo_application.R
import com.route.todo_application.databinding.ActivityMainBinding
import com.route.todo_application.ui.fragments.AddTaskFragment
import com.route.todo_application.ui.fragments.SettingsFragment

class HomeActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    val listFragment = ListFragment()
    val settingsFragment = SettingsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragments(listFragment)
        bottomNavView()
    }

    private fun bottomNavView(){
        binding.fabAddTask.setOnClickListener {
            val addTaskFragment = AddTaskFragment()
           addTaskFragment.show(supportFragmentManager," ")
        }
       binding.bottomNavView.setOnItemSelectedListener {
           if(it.itemId == R.id.settings){
               replaceFragments(settingsFragment)
           }
           if(it.itemId == R.id.tasks){
               replaceFragments(listFragment)
           }
           return@setOnItemSelectedListener true
       }
   }
    private fun replaceFragments(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .commit()
    }
}