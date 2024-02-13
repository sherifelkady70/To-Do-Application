package com.route.todo_application.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.route.todo_application.Constant
import com.route.todo_application.database.MyDatabase
import com.route.todo_application.database.model.Todo
import com.route.todo_application.databinding.ContentTaskDetailsBinding

class EditTaskActivity : AppCompatActivity() {
    lateinit var binding : ContentTaskDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ContentTaskDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getIntentAndUpdate()
    }

    private fun getIntentAndUpdate(){
        val title = intent.getStringExtra(Constant.TITLEKEY)
        val description = intent.getStringExtra(Constant.DESKEY)
        val date = intent.getLongExtra(Constant.DATEKEY,0)
        val id = intent.getIntExtra(Constant.IDKEY,0)

        binding.title.editableText.append(title)
        binding.description.editableText.append(description)
        binding.saveBtn.setOnClickListener {
            val todo =
                Todo(id=id,title=binding.title.text.toString()
                ,description=  binding.description.editableText.toString()
                ,date = date, isDone = false)
            MyDatabase.getInstance(this).getTodoDao().udpate(todo)
//            lisFragment.refreshAdapter()
            Log.e("onResume","getIntentAndUpdate")
            Log.e("onResume","title : ${ todo.title}")
            Log.e("onResume","title : ${ todo.description}")
            Log.e("onResume","title : ${ todo.date}")
            Log.e("onResume","id : ${ todo.id}")
            finish()
        }
    }
}