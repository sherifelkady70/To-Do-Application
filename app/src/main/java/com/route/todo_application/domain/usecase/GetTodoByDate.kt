package com.route.todo_application.domain.usecase

import com.route.todo_application.domain.models.TODO
import com.route.todo_application.domain.repository.TodoRepository
import javax.inject.Inject

class GetTodoByDate @Inject constructor(
    private val todoRepository : TodoRepository
) {

    suspend fun invoke(date:Long){
        todoRepository.getTodoByDate(date)
    }
}