package com.example.todo.presentation.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.ItemTaskBinding
import com.example.todo.model.presentation.Task
import com.example.todo.presentation.todolist.ToDoAdapter.ToDoVH

class ToDoAdapter() : ListAdapter<Task, ToDoVH>(Differ) {

    inner class ToDoVH(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.titleTaskTextView.text = task.title
            binding.check.isChecked = task.isCompleted
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoVH {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemTaskBinding.inflate(inflate, parent, false)
        return ToDoVH(binding)
    }

    override fun onBindViewHolder(holder: ToDoVH, position: Int) {
        val itemTask = getItem(position)
        holder.bind(itemTask)
    }

    object Differ : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }
}
