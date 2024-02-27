package com.example.todo.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todo.model.entity.TaskEntity

@Dao
interface TaskDao {

    @Query("SELECT * FROM TASKENTITY")
    suspend fun getAll(): List<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(taskEntity: TaskEntity)

    @Delete
    suspend fun delete(taskEntity: TaskEntity)
}
