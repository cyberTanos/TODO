package com.example.todo.di

import com.example.todo.data.local.TaskDao
import com.example.todo.domain.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRepository(dao: TaskDao): TaskRepository {
        val taskRepository = TaskRepository(dao)
        return taskRepository
    }
}
