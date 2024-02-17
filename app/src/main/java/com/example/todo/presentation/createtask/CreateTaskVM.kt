package com.example.todo.presentation.createtask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.domain.TaskRepository
import com.example.todo.model.presentation.Task
import com.example.todo.model.presentation.Task.Color
import com.example.todo.model.presentation.TaskColor
import com.example.todo.presentation.createtask.CreateTaskAction.InitScreen
import com.example.todo.presentation.createtask.CreateTaskAction.OnCLickSaveTask
import com.example.todo.presentation.createtask.CreateTaskAction.OnClickCloseTask
import com.example.todo.presentation.createtask.CreateTaskAction.OnColorClickTask
import com.example.todo.presentation.createtask.CreateTaskEffect.CloseScreen
import com.example.todo.presentation.createtask.CreateTaskState.Error
import com.example.todo.presentation.createtask.CreateTaskState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class CreateTaskVM @Inject constructor(
    private val repository: TaskRepository

) : ViewModel() {

    val state = MutableStateFlow<CreateTaskState>(CreateTaskState.Success())
    private val _effect = Channel<CreateTaskEffect>()
    val effect = _effect.receiveAsFlow()
    private var selectedColor = Color.PINK

    fun doAction(action: CreateTaskAction) {
        when (action) {
            is InitScreen -> fetchColors()
            is OnColorClickTask -> onSelectColor(action.taskColor)
            is OnCLickSaveTask -> saveTask(action.title, action.notes)
            is OnClickCloseTask -> navigationInToDo()
        }
    }

    private fun fetchColors() {
        viewModelScope.launch {
            val taskColors = Color.entries.map { color ->
                TaskColor(color, color == selectedColor)
            }
            state.value = Success(taskColors)
        }
    }

    private fun onSelectColor(taskColor: TaskColor) {
        viewModelScope.launch {
            selectedColor = taskColor.color
            fetchColors()
        }
    }

    private fun navigationInToDo() {
        _effect.trySend(CloseScreen)
    }

    private fun saveTask(title: String, notes: String) {
        viewModelScope.launch {
            if (title.isEmpty()) {
                val messError = "Пустое поле"
                state.value = Error(message = messError)
            } else {
                repository.saveTask(Task(title = title, notes = notes, color = selectedColor))
                _effect.trySend(CloseScreen)
            }
        }
    }
}
