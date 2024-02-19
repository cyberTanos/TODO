package com.example.todo.presentation.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.databinding.FragmentTodoBinding
import com.example.todo.presentation.createtask.CreateTaskBottomSheet
import com.example.todo.presentation.createtask.CreateTaskBottomSheet.Companion.TAG
import com.example.todo.presentation.todolist.ToDoAction.InitScreen
import com.example.todo.presentation.todolist.ToDoAction.OnClickCheckSaveTask
import com.example.todo.presentation.todolist.ToDoAction.OnClickSettings
import com.example.todo.presentation.todolist.ToDoEffect.SettingsEffect
import com.example.todo.presentation.todolist.ToDoState.Success
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ToDoFragment : Fragment(R.layout.fragment_todo) {

    private lateinit var binding: FragmentTodoBinding
    private val vm: ToDoVM by viewModels()
    private val adapter = ToDoAdapter { task ->
        vm.doAction(OnClickCheckSaveTask(task))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTodoBinding.inflate(inflater, container, false)

        bindUI()
        observeState()
        observeEffect()
        vm.doAction(InitScreen)

        return binding.root
    }

    private fun bindUI() {
        binding.recyclerTasks.adapter = adapter
        binding.addButton.setOnClickListener {
            CreateTaskBottomSheet {
                vm.doAction(InitScreen)
            }.show(childFragmentManager, TAG)
        }
        binding.settingsButton.setOnClickListener {
            vm.doAction(OnClickSettings)
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                vm.state.collect { state ->
                    if (state is Success) {
                        adapter.submitList(state.tasks)
                    }
                }
            }
        }
    }

    private fun observeEffect() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                vm.effect.collect { effect ->
                    when (effect) {
                        is SettingsEffect -> findNavController().navigate(R.id.to_settingsFragment)
                    }
                }
            }
        }
    }
}
