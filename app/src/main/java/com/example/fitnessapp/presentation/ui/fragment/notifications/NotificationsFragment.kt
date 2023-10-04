package com.example.fitnessapp.presentation.ui.fragment.notifications

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentNotificationsBinding
import com.example.fitnessapp.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationsFragment : BaseFragment<NotificationsViewModel, FragmentNotificationsBinding>(R.layout.fragment_notifications) {
    override val viewModel: NotificationsViewModel by viewModels()
    override val binding by viewBinding(FragmentNotificationsBinding::bind)

}