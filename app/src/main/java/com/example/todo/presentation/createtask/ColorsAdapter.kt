package com.example.todo.presentation.createtask

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.ItemColorBinding
import com.example.todo.model.presentation.TaskColor
import com.example.todo.presentation.createtask.ColorsAdapter.ColorVH

class ColorsAdapter(private val onColorClick: (TaskColor) -> Unit) : ListAdapter<TaskColor, ColorVH>(Differ) {

    inner class ColorVH(private val binding: ItemColorBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(taskColor: TaskColor) {
            binding.colorTask.background.colorFilter =
                BlendModeColorFilter(binding.root.context.getColor(taskColor.color.colorId), BlendMode.SRC_ATOP)
            binding.selected.isVisible = taskColor.isSelected
            binding.colorTask.setOnClickListener {
                onColorClick.invoke(taskColor)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorVH {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemColorBinding.inflate(inflate, parent, false)
        return ColorVH(binding)
    }

    override fun onBindViewHolder(holder: ColorVH, position: Int) {
        val itemColor = getItem(position)
        holder.bind(itemColor)
    }

    object Differ : DiffUtil.ItemCallback<TaskColor>() {
        override fun areItemsTheSame(oldItem: TaskColor, newItem: TaskColor): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TaskColor, newItem: TaskColor): Boolean {
            return oldItem == newItem
        }
    }
}
