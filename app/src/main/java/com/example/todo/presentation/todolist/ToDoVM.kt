package com.example.todo.presentation.todolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.domain.TaskRepository
import com.example.todo.model.presentation.Task
import com.example.todo.presentation.todolist.ToDoAction.InitScreen
import com.example.todo.presentation.todolist.ToDoAction.OnClickCheckSaveTask
import com.example.todo.presentation.todolist.ToDoAction.OnClickSettings
import com.example.todo.presentation.todolist.ToDoAction.OnClickTask
import com.example.todo.presentation.todolist.ToDoEffect.NavigateInfoTaskEffect
import com.example.todo.presentation.todolist.ToDoEffect.NavigateSettingsEffect
import com.example.todo.presentation.todolist.ToDoState.Loading
import com.example.todo.presentation.todolist.ToDoState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ToDoVM @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {
    val state = MutableStateFlow<ToDoState>(Loading)
    private val _effect = Channel<ToDoEffect>()
    val effect = _effect.receiveAsFlow()

    fun doAction(action: ToDoAction) {
        when (action) {
            is InitScreen -> fetchTasks()
            is OnClickTask -> navigationInInfoTask(action.task)
            is OnClickCheckSaveTask -> changeStateTask(action.task)
            is OnClickSettings -> navigationInSettings()
        }
    }

    private fun fetchTasks() {
        viewModelScope.launch {
            val tasks = repository.getTask()
            state.value = Success(tasks)
        }
    }

    private fun changeStateTask(task: Task) {
        viewModelScope.launch {
            val modifiedTask = task.copy(isCompleted = !task.isCompleted)
            repository.saveTask(modifiedTask)
            fetchTasks()
        }
    }

    private fun navigationInSettings() {
        _effect.trySend(NavigateSettingsEffect)
    }

    private fun navigationInInfoTask(task: Task) {
        _effect.trySend(NavigateInfoTaskEffect(task))
    }
}
