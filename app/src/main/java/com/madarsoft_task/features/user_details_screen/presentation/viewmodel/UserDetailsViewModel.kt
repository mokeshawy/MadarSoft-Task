package com.madarsoft_task.features.user_details_screen.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.madarsoft_task.features.user_details_screen.presentation.graph.UsersDetailsScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {

    val userModel = savedStateHandle.toRoute<UsersDetailsScreen>()
}