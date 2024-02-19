package com.example.todo.presentation.settings

interface SettingsAction {
    object InitScreen : SettingsAction
    object OnClickBackToDo : SettingsAction
    data class OnClickSwitch(
        val isSelected: Boolean
    ) : SettingsAction
}

data class SettingsState(
    val isDarkTheme: Boolean
)

interface SettingsEffect {
    object BackInToDoScreen : SettingsEffect
    data class ChangeTheme(
        val isChecked: Boolean
    ) : SettingsEffect
}
