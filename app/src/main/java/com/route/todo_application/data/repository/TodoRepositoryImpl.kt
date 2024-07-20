package com.route.todo_application.data.repository

import com.route.todo_application.data.datasource.offline_datasource.TodoOfflineDatasource
import com.route.todo_application.domain.models.TODO
import com.route.todo_application.domain.repository.TodoRepository

class TodoRepositoryImpl constructor(
    private val offlineDatasource:TodoOfflineDatasource
) :TodoRepository{
    override suspend fun insert(todo: TODO) {
        offlineDatasource.insert(todo)
    }

    override suspend fun delete(todo: TODO) {
        offlineDatasource.delete(todo)
    }

    override suspend fun update(todo: TODO) {
        offlineDatasource.update(todo)
    }

    override suspend fun getAll(): List<TODO> {
        return offlineDatasource.getAll()
    }

    override suspend fun getTodoByDate(date: Long): List<TODO> {
        return offlineDatasource.getTodoByDate(date)
    }
}