package com.route.todo_application.database.doa

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.route.todo_application.database.model.Todo

@Dao
interface TodoDao {
    @Insert
    fun insert(todo: Todo)
    @Delete
    fun delete(todo: Todo)
    @Update
    fun update(todo: Todo)
    @Query("select * from Todo")
    fun getAll() : List<Todo>
    @Query("select * from Todo where date = :dates ")
    fun getTodoByDate(dates : Long) : List<Todo>
}