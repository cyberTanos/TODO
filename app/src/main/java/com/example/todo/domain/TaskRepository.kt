package com.example.todo.domain

import com.example.todo.data.local.TaskDao
import com.example.todo.domain.mapper.toPresentation
import com.example.todo.model.presentation.Task
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val dao: TaskDao
) {

    suspend fun getTask(): List<Task> {
        val tasks = dao.getAll().toPresentation()
        return tasks
    }

    suspend fun saveTask(task: Task) {
        dao.insertTask(task.toPresentation())
    }
}
