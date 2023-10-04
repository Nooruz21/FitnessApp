package com.example.fitnessapp.presentation.ui.fragment.dashboard

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentDashboardBinding
import com.example.fitnessapp.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment :
    BaseFragment<DashboardViewModel, FragmentDashboardBinding>(R.layout.fragment_dashboard) {
    override val viewModel: DashboardViewModel by viewModels()
    override val binding by viewBinding(FragmentDashboardBinding::bind)
}