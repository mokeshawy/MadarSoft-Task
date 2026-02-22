package com.madarsoft_task.features.add_new_user_screen.presentation.viewmodel

import com.core.bases.base_viewmodel.BaseViewModel
import com.madarsoft_task.features.add_new_user_screen.domain.event.AddNewUserIntent
import com.madarsoft_task.features.add_new_user_screen.domain.model.state.AddNewUserState
import javax.inject.Inject

class AddNewUserViewModel @Inject constructor() :
    BaseViewModel<AddNewUserIntent, AddNewUserState>(initialState = AddNewUserState()) {


    override fun processIntent(intent: AddNewUserIntent) {
        TODO("Not yet implemented")
    }

}