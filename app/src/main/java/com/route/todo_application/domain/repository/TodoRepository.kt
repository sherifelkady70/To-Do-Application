package com.route.todo_application.domain.repository

import com.route.todo_application.domain.models.TODO

interface TodoRepository {
    suspend fun insert(todo: TODO)
    suspend fun delete(todo: TODO)
    suspend fun update(todo: TODO)
    suspend fun getAll() : List<TODO>
    suspend fun getTodoByDate(date:Long) : List<TODO>
}