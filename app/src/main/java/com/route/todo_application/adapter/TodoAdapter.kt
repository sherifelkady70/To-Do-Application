package com.route.todo_application.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.route.todo_application.database.model.Todo
import com.route.todo_application.databinding.ItemTaskBinding

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
        holder.binding.title.text = data.title
        holder.binding.time.text = data.description

        holder.itemView.setOnClickListener {
            onWholeItem.let {
               it!!.onWholeItemClick(data,position)
            }
        }
//        holder.binding.btnTaskIsDone.setOnClickListener {
//            holder.binding.btnTaskIsDone.visibility = View.INVISIBLE
//            holder.binding.doneTxt.visibility = View.VISIBLE
//            onImageClick!!.onImageClick(position)
//            Log.e("onBindViewHolder" , "in adpater : ${onImageClick!!.onImageClick(position)}")
//        }
    }
//    var onImageClick : OnImageClickListener?=null
//    interface OnImageClickListener {
//        fun onImageClick(position: Int)
//    }

    var onWholeItem : OnWholeItemClickListener?=null
    interface OnWholeItemClickListener {
        fun onWholeItemClick(data:Todo,position: Int)
    }
}