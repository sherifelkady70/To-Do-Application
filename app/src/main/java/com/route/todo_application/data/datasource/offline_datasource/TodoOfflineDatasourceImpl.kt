package com.route.todo_application.data.datasource.offline_datasource

import com.route.todo_application.data.database.MyDatabase
import com.route.todo_application.data.database.model.Todo
import com.route.todo_application.domain.models.TODO
import javax.inject.Inject

class TodoOfflineDatasourceImpl @Inject constructor(
    private val database: MyDatabase
): TodoOfflineDatasource {
    override suspend fun insert(todo: TODO) {
        val editedTodo = Todo(id=todo.id,
            title = todo.title,
            description = todo.description,
            date = todo.date,
            isDone = todo.isDone)
        database.getTodoDao().insert(editedTodo)
    }

    override suspend fun delete(todo: TODO) {
        val editedTodo = Todo(id=todo.id,
            title = todo.title,
            description = todo.description,
            date = todo.date,
            isDone = todo.isDone)
        database.getTodoDao().delete(editedTodo)
    }

    override suspend fun update(todo: TODO) {
        val editedTodo = Todo(id=todo.id,
            title = todo.title,
            description = todo.description,
            date = todo.date,
            isDone = todo.isDone)
        database.getTodoDao().update(editedTodo)
    }

    override suspend fun getAll(): List<TODO> {
        return database.getTodoDao().getAll().map {
            it.toTodoDomain()
        }
    }

    override suspend fun getTodoByDate(date: Long): List<TODO> {
        return database.getTodoDao().getTodoByDate(date).map {
            it.toTodoDomain()
        }
    }
}