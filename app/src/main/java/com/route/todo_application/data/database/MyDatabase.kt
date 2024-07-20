package com.route.todo_application.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.route.todo_application.data.database.doa.TodoDao
import com.route.todo_application.data.database.model.Todo

@Database(entities = [Todo::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

     abstract fun getTodoDao() : TodoDao

     companion object{
         private const val NAME_DATABASE = "Todo Database"
         var database : MyDatabase? = null

         fun getInstance(context : Context) : MyDatabase {
             if(database == null){
                 database = Room.databaseBuilder(context.applicationContext
                     , MyDatabase::class.java,
                     NAME_DATABASE
                 )
                     .fallbackToDestructiveMigration()
                     .allowMainThreadQueries()
                     .build()
             }
                 return database!!
         }
     }
}