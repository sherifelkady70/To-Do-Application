package com.route.todo_application.domain.models

data class TODO(

    val id: Int =0,
    var title: String,
    var description: String,
    var date: Long,
    var isDone: Boolean
)
