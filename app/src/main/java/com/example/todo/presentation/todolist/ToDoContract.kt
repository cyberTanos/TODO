package com.example.todo.presentation.todolist

import com.example.todo.model.presentation.Task

interface ToDoAction {
    object InitScreen : ToDoAction
}

interface ToDoState {
    object Loading : ToDoState
    data class Success(
        val tasks: List<Task>
    ) : ToDoState
}
