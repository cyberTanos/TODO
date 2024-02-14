package com.example.todo.presentation.createtask

import com.example.todo.R
import com.example.todo.model.presentation.Task.Color

interface CreateTaskAction {
    object InitScreen : CreateTaskAction
    data class OnCLickSaveTask(
        val title: String,
        val notes: String = "",
        val color: Int = R.color.itemPink
    ) : CreateTaskAction
}

interface CreateTaskState {
    data class Success(
        val colors: List<Color> = Color.entries
    ) : CreateTaskState
}
