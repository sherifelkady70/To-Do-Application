package com.route.todo_application.data.datasource.offline_datasource

import com.route.todo_application.data.database.model.Todo
import com.route.todo_application.domain.models.TODO

interface TodoOfflineDatasource {
    suspend fun insert(todo:TODO)
    suspend fun delete(todo: TODO)
    suspend fun update(todo: TODO)
    suspend fun getAll() : List<TODO>
    suspend fun getTodoByDate(date:Long) : List<TODO>
}