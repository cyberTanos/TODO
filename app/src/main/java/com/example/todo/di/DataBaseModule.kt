package com.example.todo.di

import android.content.Context
import androidx.room.Room
import com.example.todo.data.local.TaskDao
import com.example.todo.data.local.TaskDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    fun provideDataBase(@ApplicationContext applicationContext: Context): TaskDataBase {
        val db = Room.databaseBuilder(
            applicationContext,
            TaskDataBase::class.java, "database"
        ).build()
        return db
    }

    @Provides
    fun provideTaskDao(dataBase: TaskDataBase): TaskDao {
        val daoTask = dataBase.taskDao()
        return daoTask
    }
}
