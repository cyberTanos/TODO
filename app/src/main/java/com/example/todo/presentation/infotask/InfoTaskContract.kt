package com.example.todo.presentation.infotask

import com.example.todo.model.presentation.Task

interface InfoTaskAction {
    object OnClickBackToDo : InfoTaskAction
    data class OnClickDelete(
        val task: Task
    ) : InfoTaskAction
}

interface InfoTaskEffect {
    object BackInToDoScreen : InfoTaskEffect
}
