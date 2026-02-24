package com.madarsoft_task.features.add_user_screen.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.core.ui_component.custom_text_failed.CustomTextField
import com.core.ui_component.dialog_component.DialogComponent
import com.core.ui_component.main_top_bar.MainTopBar
import com.holouly.core.ui_component.ui_text.UiText
import com.madarsoft_task.R
import com.madarsoft_task.features.add_user_screen.domain.enums.GenderType
import com.madarsoft_task.features.add_user_screen.domain.model.state.AddUserState
import com.madarsoft_task.features.add_user_screen.presentation.viewmodel.AddUserViewModel
import com.madarsoft_task.features.common.composable.UserPopUpContent
import com.madarsoft_task.features.common.composable.componenet.PrimaryButton
import com.madarsoft_task.features.common.composable.componenet.RadioButtonWithText
import com.madarsoft_task.features.common.domain.model.ui.UserUiModel
import com.madarsoft_task.ui.theme.NaturalLightActive
import com.madarsoft_task.ui.theme.NaturalNormal
import com.madarsoft_task.ui.theme.OnPrimary
import com.madarsoft_task.ui.theme.Primary
import com.madarsoft_task.ui.theme.regular


@Composable
fun AddUserScreen(
    viewModel: AddUserViewModel = hiltViewModel(),
    onNavigateToUserDetails: (UserUiModel) -> Unit,
    onRightIconClicked: () -> Unit
) {

    val uiState = viewModel.uiStateFlow.collectAsStateWithLifecycle().value

    MainTopBar(
        title = R.string.add_user,
        isRefreshing = uiState.isLoading,
        rightIcon = R.drawable.ic_vector_lists,
        onRightIconClicked = onRightIconClicked,
        content = {

            AddUserContent(
                state = uiState,
                isAddUserBtnEnabled = viewModel.isAddUserBtnEnabled,
                onNameValueChange = viewModel::sendNameValidationIntent,
                onAgeValueChange = viewModel::sendAgeValidationIntent,
                onJobTitleValueChange = viewModel::sendJobTitleValidationIntent,
                onSelectGenderType = viewModel::sendGenderValidationIntent,
                onAddUserBtnClicked = { viewModel.sendAddUserIntent() })


        })

    LaunchedEffect(key1 = uiState) {
        if (uiState.isAddedSuccess) {
            viewModel.showAddUserSuccessPopup = true
        }
    }

    SuccessfulAddUserPopup(
        showDialog = viewModel.showAddUserSuccessPopup,
        name = uiState.name,
        onShowDetailsClicked = {
            viewModel.onNavigateToUserDetailsScreen(onNavigateToUserDetails)
        },
        onCancel = {
            viewModel.onDismissSuccessfulAddUserPopup()
        }
    )
}

@Composable
fun AddUserContent(
    modifier: Modifier = Modifier,
    state: AddUserState,
    isAddUserBtnEnabled: Boolean,
    onNameValueChange: (String) -> Unit,
    onAgeValueChange: (String) -> Unit,
    onJobTitleValueChange: (String) -> Unit,
    onSelectGenderType: (String) -> Unit,
    onAddUserBtnClicked: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(state = rememberScrollState())
            .padding(all = 16.dp),
    ) {

        Text(
            text = stringResource(id = R.string.please_enter_user_info_to_add_new_user),
            style = MaterialTheme.typography.bodyLarge.regular.copy(color = Primary),
            fontSize = 16.sp,
        )

        Spacer(modifier = modifier.height(8.dp))

        NameTextFailed(
            nameTextValue = state.name,
            nameMessage = state.nameError,
            onNameValueChange = onNameValueChange
        )

        Spacer(modifier = modifier.height(8.dp))

        AgeTextFailed(
            ageTextValue = state.age,
            ageMessage = state.ageError,
            onAgeValueChange = onAgeValueChange,
        )

        Spacer(modifier = modifier.height(8.dp))

        JobTitleTextFailed(
            jobTitleTextValue = state.jobTitle,
            jobTitleMessage = state.jobTitleError,
            onJobTitleValueChange = onJobTitleValueChange
        )

        Spacer(modifier = modifier.height(12.dp))

        GenderTypeSelector(
            genderType = state.genderType,
            genderTypeMessage = state.genderTypeError,
            onSelectGenderType = onSelectGenderType
        )

        Spacer(modifier = modifier.height(30.dp))

        PrimaryButton(
            modifier = modifier.fillMaxWidth(),
            isEnabled = isAddUserBtnEnabled,
            content = {
                Text(
                    text = stringResource(id = R.string.add_user),
                    style = MaterialTheme.typography.bodyLarge.regular.copy(color = OnPrimary),
                    fontSize = 16.sp,
                )
            }, onClick = onAddUserBtnClicked
        )
    }
}

@Composable
fun NameTextFailed(
    nameTextValue: String,
    nameMessage: UiText?,
    onNameValueChange: (String) -> Unit,
) {
    CustomTextField(
        placeholder = stringResource(id = R.string.name),
        text = nameTextValue,
        textStyle = MaterialTheme.typography.bodyLarge.copy(color = NaturalNormal),
        focusedColor = NaturalLightActive,
        unFocusedColor = NaturalLightActive,
        onValueChange = onNameValueChange,
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Next,
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        isError = nameMessage != null,
        errorMessage = nameMessage,
    )
}


@Composable
fun AgeTextFailed(
    ageTextValue: String,
    ageMessage: UiText?,
    onAgeValueChange: (String) -> Unit,
) {
    CustomTextField(
        placeholder = stringResource(id = R.string.age),
        text = ageTextValue,
        textStyle = MaterialTheme.typography.bodyLarge.copy(color = NaturalNormal),
        focusedColor = NaturalLightActive,
        unFocusedColor = NaturalLightActive,
        onValueChange = onAgeValueChange,
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Next,
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        isError = ageMessage != null,
        errorMessage = ageMessage,
    )
}


@Composable
fun JobTitleTextFailed(
    jobTitleTextValue: String,
    jobTitleMessage: UiText?,
    onJobTitleValueChange: (String) -> Unit,
) {
    CustomTextField(
        placeholder = stringResource(id = R.string.job_title),
        text = jobTitleTextValue,
        textStyle = MaterialTheme.typography.bodyLarge.copy(color = NaturalNormal),
        focusedColor = NaturalLightActive,
        unFocusedColor = NaturalLightActive,
        onValueChange = onJobTitleValueChange,
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Done,
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        isError = jobTitleMessage != null,
        errorMessage = jobTitleMessage,
    )
}

@Composable
fun GenderTypeSelector(
    genderType: String?,
    genderTypeMessage: UiText?,
    onSelectGenderType: (String) -> Unit
) {

    var selectedOption by remember { mutableStateOf(value = genderType) }

    Column {
        Text(
            text = stringResource(id = R.string.select_gender),
            fontSize = 16.sp,
            style = MaterialTheme.typography.titleMedium.regular.copy(color = Primary),
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(60.dp)
        ) {
            RadioButtonWithText(GenderType.MALE.value, selectedOption) {
                selectedOption = it
                onSelectGenderType(it)
            }

            RadioButtonWithText(GenderType.FEMALE.value, selectedOption) {
                selectedOption = it
                onSelectGenderType(it)
            }
        }

        genderTypeMessage?.let {
            Text(
                text = stringResource(id = R.string.select_gender_type),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}


@Composable
fun SuccessfulAddUserPopup(
    showDialog: Boolean,
    name: String,
    onShowDetailsClicked: () -> Unit,
    onCancel: () -> Unit
) {
    DialogComponent(
        showDialog = showDialog,
        content = {
            UserPopUpContent(
                name = name,
                icon = R.drawable.ic_vector_cloud_done,
                titleRes = R.string.user_added_success,
                leftBtnRes = R.string.show,
                rightBtnRes = R.string.cancel,
                onLeftBtnClicked = onShowDetailsClicked,
                onRightBtnClicked = onCancel
            )
        })
}


@Composable
@Preview(showBackground = true)
fun AddUserContent() {
    AddUserContent(
        state = state,
        isAddUserBtnEnabled = false,
        onNameValueChange = {},
        onAgeValueChange = {},
        onJobTitleValueChange = { },
        onSelectGenderType = {},
        onAddUserBtnClicked = {})
}

val state = AddUserState(
    name = "Mohamed Keshawey",
    age = "38",
    jobTitle = "Senior Android Developer",
    genderType = GenderType.MALE.name
)


