package com.madarsoft_task.features.add_new_user_screen.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.core.ui_component.custom_text_failed.CustomTextField
import com.core.ui_component.main_top_bar.MainTopBar
import com.holouly.core.ui_component.ui_text.UiText
import com.madarsoft_task.R
import com.madarsoft_task.features.add_new_user_screen.domain.enums.UserGender
import com.madarsoft_task.features.add_new_user_screen.domain.model.state.AddNewUserState
import com.madarsoft_task.ui.theme.NaturalLightActive
import com.madarsoft_task.ui.theme.NaturalNormal
import com.madarsoft_task.ui.theme.Secondary
import com.madarsoft_task.ui.theme.regular


@Composable
fun AddNewUserScreen(
    onRightIconClicked: () -> Unit
) {
    MainTopBar(
        title = R.string.add_new_user,
        isPullRefresh = false,
        rightIcon = R.drawable.ic_vector_lists,
        onRightIconClicked = onRightIconClicked,
        content = {

        })
}

@Composable
fun AddNewUserContent(
    modifier: Modifier = Modifier,
    state: AddNewUserState,
    onNameValueChange: (String) -> Unit,
    onAgeValueChange: (String) -> Unit,
    onJobTitleValueChange: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(state = rememberScrollState())
            .padding(all = 16.dp),
    ) {

        Icon(
            modifier = modifier.size(80.dp),
            painter = painterResource(id = R.drawable.ic_vector_add_user),
            contentDescription = "Add New User",
            tint = Color(0xFF3678CF)
        )
        Text(
            text = stringResource(id = R.string.please_enter_user_info_to_add_new_user),
            style = MaterialTheme.typography.bodyLarge.regular.copy(color = Secondary),
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
        placeholder = stringResource(id = R.string.name),
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
        placeholder = stringResource(id = R.string.name),
        text = jobTitleTextValue,
        textStyle = MaterialTheme.typography.bodyLarge.copy(color = NaturalNormal),
        focusedColor = NaturalLightActive,
        unFocusedColor = NaturalLightActive,
        onValueChange = onJobTitleValueChange,
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Next,
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        isError = jobTitleMessage != null,
        errorMessage = jobTitleMessage,
    )
}

@Composable
@Preview(showBackground = true)
fun AddNewUserContent() {
    AddNewUserContent(
        state = state,
        onNameValueChange = {},
        onAgeValueChange = {},
        onJobTitleValueChange = { })
}

val state = AddNewUserState(
    name = "Mohamed Keshawey",
    age = "38",
    jobTitle = "Senior Android Developer",
    userGender = UserGender.MALE.name
)


