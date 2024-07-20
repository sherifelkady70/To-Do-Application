package com.route.todo_application.data.datasource.di

import com.route.todo_application.data.datasource.offline_datasource.TodoOfflineDatasource
import com.route.todo_application.data.datasource.offline_datasource.TodoOfflineDatasourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DatasourceModule {
    @Provides
    abstract fun provideOfflineDatasource(impl:TodoOfflineDatasourceImpl)
    :TodoOfflineDatasource
}