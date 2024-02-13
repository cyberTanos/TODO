package com.example.todo.presentation.createtask

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuff.Mode.SRC_ATOP
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.ItemColorBinding
import com.example.todo.model.presentation.Task
import com.example.todo.model.presentation.Task.Color
import com.example.todo.presentation.createtask.ColorsAdapter.ColorVH

class ColorsAdapter() : ListAdapter<Color, ColorVH>(Differ) {

    class ColorVH(private val binding: ItemColorBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(color: Color) {
            binding.colorTask.background.colorFilter = BlendModeColorFilter(binding.root.context.getColor(color.colorId), BlendMode.SRC_ATOP)
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

    object Differ : DiffUtil.ItemCallback<Color>() {
        override fun areItemsTheSame(oldItem: Color, newItem: Color): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Color, newItem: Color): Boolean {
            return oldItem == newItem
        }
    }
}