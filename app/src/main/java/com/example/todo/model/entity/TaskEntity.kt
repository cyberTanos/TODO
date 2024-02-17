package com.example.todo.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todo.model.presentation.Task.Color
import com.example.todo.model.presentation.Task.Color.PINK

@Entity
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val notes: String,
    val color: Color,
    val isCompleted: Boolean = false
)
