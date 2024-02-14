package com.example.todo.presentation.createtask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.presentation.createtask.CreateTaskAction.InitScreen
import com.example.todo.presentation.createtask.CreateTaskAction.OnCLickSaveTask
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class CreateTaskVM @Inject constructor(

) : ViewModel() {

    val state = MutableStateFlow<CreateTaskState>(CreateTaskState.Success())

    fun doAction(action: CreateTaskAction) {
        when (action) {
            is InitScreen -> fetchColors()
            is OnCLickSaveTask -> saveTask()
        }
    }

    private fun fetchColors() {
        viewModelScope.launch {

        }
    }

    private fun saveTask() {
        viewModelScope.launch {

        }
    }
}
