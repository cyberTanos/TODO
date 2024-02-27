package com.example.todo.presentation.infotask

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
import com.example.todo.databinding.FragmentInfoTaskBinding
import com.example.todo.model.presentation.Task
import com.example.todo.presentation.infotask.InfoTaskAction.OnClickBackToDo
import com.example.todo.presentation.infotask.InfoTaskAction.OnClickDelete
import com.example.todo.presentation.infotask.InfoTaskEffect.BackInToDoScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InfoTaskFragment : Fragment(R.layout.fragment_info_task) {

    private lateinit var binding: FragmentInfoTaskBinding
    private val vm: InfoTaskVM by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentInfoTaskBinding.inflate(inflater, container, false)

        bindUI()
        observeEffect()

        return binding.root
    }

    private fun bindUI() {
        val task = arguments?.getParcelable<Task>(TASK_INFO_KEY)
        binding.titleTaskText.text = task?.title
        binding.notesTaskText.text = task?.notes

        binding.backButton.setOnClickListener {
            vm.doAction(OnClickBackToDo)
        }
        binding.deleteButton.setOnClickListener {
            if (task != null) {
                vm.doAction(OnClickDelete(task))
            }
        }
    }

    private fun observeEffect() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                vm.effect.collect { effect ->
                    when (effect) {
                        is BackInToDoScreen -> findNavController().popBackStack()
                    }
                }
            }
        }
    }

    companion object {
        const val TASK_INFO_KEY = "task_info"
    }
}
