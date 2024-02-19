package com.example.todo.presentation.todolist

import com.example.todo.model.presentation.Task

interface ToDoAction {
    object InitScreen : ToDoAction
    data class OnClickCheckSaveTask(
        val task: Task
    ) : ToDoAction

    object OnClickSettings : ToDoAction
}

interface ToDoState {
    object Loading : ToDoState
    data class Success(
        val tasks: List<Task>
    ) : ToDoState
}

interface ToDoEffect {
    object SettingsEffect : ToDoEffect
}
