package com.example.todo.model.presentation

import android.os.Parcelable
import androidx.annotation.ColorRes
import com.example.todo.R
import com.example.todo.model.presentation.Task.Color.PINK
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    val id: Int = 0,
    val title: String,
    val notes: String = "",
    val color: Color = PINK,
    val isCompleted: Boolean = false
) : Parcelable {

    enum class Color(@ColorRes val colorId: Int) {
        PINK(R.color.itemPink),
        ORANGE(R.color.itemOrange),
        YELLOW(R.color.itemYellow),
        LIGHT_GREEN(R.color.itemLightGreen),
        GREEN(R.color.itemGreen),
        PURPLE(R.color.itemPurple),
        BLUE(R.color.itemBlue),
        LIGHT_BLUE(R.color.itemLightBlue)
    }
}
