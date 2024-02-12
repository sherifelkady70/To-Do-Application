package com.route.todo_application.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.route.todo_application.Constant
import com.route.todo_application.database.MyDatabase
import com.route.todo_application.database.model.Todo
import com.route.todo_application.databinding.ContentTaskDetailsBinding

class EditTaskActivity : AppCompatActivity() {
    lateinit var binding : ContentTaskDetailsBinding
    //var lisFragment = ListFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ContentTaskDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getIntentAndUpdate()
    }

    fun getIntentAndUpdate(){
        val title = intent.getStringExtra(Constant.TITLEKEY)
        val description = intent.getStringExtra(Constant.DESKEY)
        val date = intent.getLongExtra(Constant.DATEKEY,0)
        val id = intent.getIntExtra(Constant.IDKEY,0)
        binding.title.editableText.append(title)
        binding.description.editableText.append(description)
        binding.saveBtn.setOnClickListener {
            val todo = Todo(id=id,title=binding.title.editableText.toString()
                ,description=  binding.description.editableText.toString()
                ,date = date, isDone = false)
            MyDatabase.getInstance(this.applicationContext).getTodoDao().udpate(todo)
//            lisFragment.refreshAdapter()
            finish()
        }
    }
}