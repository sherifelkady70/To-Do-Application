package com.route.todo_application.data.di

import android.content.Context
import androidx.room.Dao
import androidx.room.Room
import com.route.todo_application.data.database.MyDatabase
import com.route.todo_application.data.database.doa.TodoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun getDatabaseManager(context: Context) : MyDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            MyDatabase::class.java,
            "Todo Database"
        ).build()
    }

    @Provides
    fun getDAO(database:MyDatabase) : TodoDao{
        return database.getTodoDao()
    }
}