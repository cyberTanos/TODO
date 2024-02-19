package com.example.todo.presentation.settings

import androidx.lifecycle.ViewModel
import com.example.todo.data.local.ThemeDataSource
import com.example.todo.presentation.settings.SettingsAction.OnClickBackToDo
import com.example.todo.presentation.settings.SettingsAction.OnClickSwitch
import com.example.todo.presentation.settings.SettingsEffect.BackInToDoScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

@HiltViewModel
class SettingsVM @Inject constructor(
    private val theme: ThemeDataSource
) : ViewModel() {

    val state = MutableStateFlow(SettingsState(theme.getIsDark()))
    private val _effect = Channel<SettingsEffect>()
    val effect = _effect.receiveAsFlow()

    fun doAction(action: SettingsAction) {
        when (action) {
            is OnClickBackToDo -> navigationInToDo()
            is OnClickSwitch -> changeTheme(action.isSelected)
        }
    }

    private fun navigationInToDo() {
        _effect.trySend(BackInToDoScreen)
    }

    private fun changeTheme(isChecked: Boolean) {
        theme.putIsDark(isChecked)
        _effect.trySend(SettingsEffect.ChangeTheme(isChecked))
    }
}
