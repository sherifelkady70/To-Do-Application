package com.route.todo_application.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.route.todo_application.R
import com.route.todo_application.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private val listFragment = ListFragment()
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
                if(binding.bottomNavView.selectedItemId == R.id.tasks){
                    listFragment.refreshAdapter()
                }else{
                    replaceFragments(listFragment)
                }
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