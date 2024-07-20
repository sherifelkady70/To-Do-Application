package com.route.todo_application.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.route.todo_application.domain.models.TODO

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int =0,
    @ColumnInfo //specify details about a column in a SQLite database table. It provides metadata about the column such as its name, whether it is a primary key, whether it should be indexed, and more.
    var title: String,
    @ColumnInfo
    var description: String,
    @ColumnInfo
    var date: Long,
    @ColumnInfo
    var isDone: Boolean
        ){
    fun toTodoDomain() : TODO{
        return TODO(id=id,title=title,
            description=description, date = date,isDone=isDone)
    }
}