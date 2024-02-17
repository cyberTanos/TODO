package com.example.todo.domain.mapper

import com.example.todo.model.entity.TaskEntity
import com.example.todo.model.presentation.Task

fun TaskEntity.toPresentation(): Task {
    return Task(
        id = id,
        title = title,
        notes = notes,
        color = color,
        isCompleted = isCompleted
    )
}

fun List<TaskEntity>.toPresentation(): List<Task> {
    return this.map { it.toPresentation() }
}

fun Task.toData(): TaskEntity {
    return TaskEntity(
        id = id,
        title = title,
        notes = notes,
        color = color,
        isCompleted = isCompleted
    )
}
