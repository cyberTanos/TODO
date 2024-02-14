package com.example.todo.presentation.createtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.todo.R
import com.example.todo.databinding.FragmentTaskBinding
import com.example.todo.presentation.createtask.CreateTaskAction.OnCLickSaveTask
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CreateTaskFragment : BottomSheetDialogFragment(R.layout.fragment_task) {

    private lateinit var binding: FragmentTaskBinding
    private val vm: CreateTaskVM by viewModels()
    private val adapter = ColorsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTaskBinding.inflate(inflater, container, false)

        bindUI()
        observeState()
        vm.doAction(CreateTaskAction.InitScreen)

        return binding.root
    }

    private fun bindUI() {
        binding.recyclerColor.adapter = adapter
        binding.closeButton.setOnClickListener {
            dismiss()
        }
        binding.saveTaskView.setOnClickListener {
            vm.doAction(
                OnCLickSaveTask(
                    title = binding.titleEditText.text.toString()
                )
            )
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                vm.state.collect { state ->
                    if (state is CreateTaskState.Success) {
                        adapter.submitList(state.colors)
                    }
                }
            }
        }
    }
}
