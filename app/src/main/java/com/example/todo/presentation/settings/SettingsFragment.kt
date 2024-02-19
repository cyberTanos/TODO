package com.example.todo.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.databinding.FragmentSettingsBinding
import com.example.todo.presentation.settings.SettingsAction.InitScreen
import com.example.todo.presentation.settings.SettingsAction.OnClickBackToDo
import com.example.todo.presentation.settings.SettingsAction.OnClickSwitch
import com.example.todo.presentation.settings.SettingsEffect.BackInToDoScreen
import com.example.todo.presentation.settings.SettingsEffect.ChangeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private lateinit var binding: FragmentSettingsBinding
    private val vm: SettingsVM by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        bindUI()
        observeState()
        observeEffect()
        vm.doAction(InitScreen)

        return binding.root
    }

    private fun bindUI() {
        binding.backButton.setOnClickListener {
            vm.doAction(OnClickBackToDo)
        }
        binding.switchMaterial.setOnCheckedChangeListener { _, isChecked ->
            vm.doAction(OnClickSwitch(isChecked))
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                vm.state.collect { state ->
                    binding.switchMaterial.isChecked = state.isDarkTheme
                }
            }
        }
    }

    private fun observeEffect() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                vm.effect.collect { effect ->
                    when (effect) {
                        is BackInToDoScreen -> findNavController().popBackStack()
                        is ChangeTheme -> {
                            if (effect.isChecked) {
                                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                            } else {
                                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                            }
                        }
                    }
                }
            }
        }
    }
}
