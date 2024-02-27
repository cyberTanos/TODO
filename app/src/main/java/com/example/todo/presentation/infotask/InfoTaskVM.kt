package com.example.todo.presentation.infotask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.domain.TaskRepository
import com.example.todo.model.presentation.Task
import com.example.todo.presentation.infotask.InfoTaskAction.OnClickBackToDo
import com.example.todo.presentation.infotask.InfoTaskAction.OnClickDelete
import com.example.todo.presentation.infotask.InfoTaskEffect.BackInToDoScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class InfoTaskVM @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    private val _effect = Channel<InfoTaskEffect>()
    val effect = _effect.receiveAsFlow()

    fun doAction(action: InfoTaskAction) {
        when (action) {
            is OnClickBackToDo -> navigateInToDo()
            is OnClickDelete -> deleteTask(action.task)
        }
    }

    private fun navigateInToDo() {
        _effect.trySend(BackInToDoScreen)
    }

    private fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
            _effect.trySend(BackInToDoScreen)
        }
    }
}
