package com.route.todo_application.data.repository.di

import com.route.todo_application.data.repository.TodoRepositoryImpl
import com.route.todo_application.domain.repository.TodoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule  {

    @Binds
    abstract fun provideTodoRepository(impl:TodoRepositoryImpl)
    :TodoRepository
}