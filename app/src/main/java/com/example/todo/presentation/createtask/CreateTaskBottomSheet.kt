package com.example.todo.presentation.createtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.todo.R
import com.example.todo.databinding.BottomSheetCreateTaskBinding
import com.example.todo.presentation.createtask.CreateTaskAction.OnCLickSaveTask
import com.example.todo.presentation.createtask.CreateTaskAction.OnClickCloseTask
import com.example.todo.presentation.createtask.CreateTaskAction.OnColorClickTask
import com.example.todo.presentation.createtask.CreateTaskEffect.CloseScreen
import com.example.todo.presentation.createtask.CreateTaskState.Error
import com.example.todo.presentation.createtask.CreateTaskState.Success
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CreateTaskBottomSheet : BottomSheetDialogFragment(R.layout.bottom_sheet_create_task) {

    private lateinit var binding: BottomSheetCreateTaskBinding
    private val vm: CreateTaskVM by viewModels()
    private val adapter = ColorsAdapter { taskColor ->
        vm.doAction(OnColorClickTask(taskColor))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = BottomSheetCreateTaskBinding.inflate(inflater, container, false)

        bindUI()
        observeState()
        observeEffect()
        vm.doAction(CreateTaskAction.InitScreen)

        return binding.root
    }

    private fun bindUI() {
        binding.recyclerColor.adapter = adapter
        binding.closeButton.setOnClickListener {
            vm.doAction(OnClickCloseTask)
        }
        binding.saveTaskView.setOnClickListener {
            vm.doAction(
                OnCLickSaveTask(
                    title = binding.titleEditText.text.toString(),
                    notes = binding.notesEditText.text.toString()
                )
            )
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                vm.state.collect { state ->
                    binding.errorTextView.isVisible = state is Error
                    when (state) {
                        is Success -> adapter.submitList(state.colors)
                        is Error -> binding.errorTextView.text = state.message
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
                        is CloseScreen -> dismiss()
                    }

                }
            }
        }
    }

    companion object {
        const val TAG = "CreateTaskBottomSheetTag"
    }
}
