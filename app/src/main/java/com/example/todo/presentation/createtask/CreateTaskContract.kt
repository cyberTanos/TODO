package com.example.todo.presentation.createtask

import com.example.todo.model.presentation.TaskColor

interface CreateTaskAction {
    object InitScreen : CreateTaskAction
    data class OnColorClickTask(
        val taskColor: TaskColor
    ) : CreateTaskAction

    object OnClickCloseTask : CreateTaskAction

    data class OnCLickSaveTask(
        val title: String,
        val notes: String = ""
    ) : CreateTaskAction
}

interface CreateTaskState {
    data class Success(
        val colors: List<TaskColor> = emptyList()
    ) : CreateTaskState

    data class Error(
        val message: String
    ) : CreateTaskState
}

interface CreateTaskEffect {
    object CloseScreen : CreateTaskEffect
}
