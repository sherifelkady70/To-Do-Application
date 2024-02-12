package com.route.todo_application.ui.activities

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.route.todo_application.databinding.TaskDetailsActivityBinding

class EditTaskActivity : AppCompatActivity() {
    lateinit var binding : TaskDetailsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TaskDetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}