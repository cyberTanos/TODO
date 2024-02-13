package com.example.todo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.model.presentation.Task
import com.example.todo.presentation.ToDoAction.InitScreen
import com.example.todo.presentation.ToDoState.Loading
import com.example.todo.presentation.ToDoState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ToDoVM @Inject constructor(

) : ViewModel() {
    val state = MutableStateFlow<ToDoState>(Loading)

    fun doAction(action: ToDoAction) {
        when (action) {
            is InitScreen -> fetchTasks()
        }
    }

    private fun fetchTasks() {
        viewModelScope.launch {
            val tasks = listOf(
                Task(title = "Работать"),
                Task(title = "Кодить"),
                Task(title = "Кодить"),
                Task(title = "Кодить"),
                Task(title = "Кодить"),
                Task(title = "Кодить"),
                Task(title = "Кодить"),
                Task(title = "Кодить"),
                Task(title = "Кодить"),
                Task(title = "Кодить"),
                Task(title = "Кодить"),
                Task(title = "Кодить"),
                Task(title = "Кодить"),
                Task(title = "Кодить"),
                Task(title = "Кодить"),
                Task(title = "Кодить"),
                Task(title = "Кодить"),
                Task(title = "Поужинать")
            )
            state.value = Success(tasks)
        }
    }
}
