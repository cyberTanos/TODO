package com.example.todo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todo.model.entity.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskDataBase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
