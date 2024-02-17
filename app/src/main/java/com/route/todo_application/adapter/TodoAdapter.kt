package com.route.todo_application.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.route.todo_application.R
import com.route.todo_application.database.model.Todo
import com.route.todo_application.databinding.ItemTaskBinding
import com.zerobranch.layout.SwipeLayout

class TodoAdapter(var todoList: List<Todo>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

    fun updateNewList(newTodoList : List<Todo>){
        todoList = newTodoList
        notifyDataSetChanged()
    }
    class TodoViewHolder(val binding : ItemTaskBinding) : RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val v = ItemTaskBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TodoViewHolder(v)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val data : Todo = todoList[position]
        if(data.isDone){
            Log.e("btnTaskIsDone" , "isDone inside onBindMethod : ${data.isDone}")
            holder.binding.apply {
                btnTaskIsDone.visibility = View.INVISIBLE
                doneTxt.visibility = View.VISIBLE
                title.setTextColor(R.drawable.textdone)
                time.setTextColor(R.drawable.textdone)
            }
        }
        holder.binding.title.text = data.title
        holder.binding.time.text = data.description


        holder.binding.swipeLayout.setOnActionsListener(object : SwipeLayout.SwipeActionsListener{
            override fun onOpen(direction: Int, isContinuous: Boolean) {
                if(direction == SwipeLayout.RIGHT) {
                    holder.binding.swipeLayout.close(true)
                    onDeleteItem!!.onDeleteClick(data,position)
                }
                if(direction == SwipeLayout.LEFT) {
                    holder.binding.swipeLayout.close(true)
                    onEditClick.let {
                        it!!.onEditItemClick(data,position)
                    }
                }
            }

            override fun onClose() {
            }
        })
        holder.binding.btnTaskIsDone.setOnClickListener {
            onImageDoneClick!!.onImageDoneClick(data,position)
            Log.e("btnTaskIsDone" , "isDone before updated : ${onImageDoneClick!!.onImageDoneClick(data,position)}")
            if(data.isDone){//isDone = true
                Log.e("btnTaskIsDone" , "isDone after2  updated : ${onImageDoneClick!!.onImageDoneClick(data,position)}")
                Log.e("btnTaskIsDone" , "isDone after updated : ${data.isDone}")
                holder.binding.apply {
                    btnTaskIsDone.visibility = View.INVISIBLE
                    doneTxt.visibility = View.VISIBLE
                    title.setTextColor(R.drawable.textdone)
                    time.setTextColor(R.drawable.textdone)
                }
                Log.e("onBindViewHolder" , "in adapter : Done Image ")
            }else{
                holder.binding.btnTaskIsDone.visibility = View.VISIBLE
                holder.binding.doneTxt.visibility = View.INVISIBLE
            }
        }
    }
    var onImageDoneClick : OnImageDoneClickListener?=null
    interface OnImageDoneClickListener {
        fun onImageDoneClick(data: Todo, position: Int) : Boolean
    }

    var onEditClick : OnEditClickListener?=null
    interface OnEditClickListener {
        fun onEditItemClick(data:Todo, position: Int)
    }
    var onDeleteItem : OnItemDeleteListener?=null
    interface OnItemDeleteListener{
        fun onDeleteClick(data:Todo,position: Int)
    }
}