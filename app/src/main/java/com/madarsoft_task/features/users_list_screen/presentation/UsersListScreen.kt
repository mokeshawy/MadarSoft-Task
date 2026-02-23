package com.madarsoft_task.features.users_list_screen.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.core.ui_component.failure_view.FailureView
import com.core.ui_component.main_top_bar.MainTopBar
import com.core.ui_component.ui_extensions.noRippleClickable
import com.core.ui_component.ui_generic.GeneralLazyColumn
import com.madarsoft_task.R
import com.madarsoft_task.features.common.domain.model.ui.UserUiModel
import com.madarsoft_task.features.users_list_screen.presentation.viewmodel.UsersListViewModel
import com.madarsoft_task.ui.theme.NaturalLight
import com.madarsoft_task.ui.theme.Primary
import com.madarsoft_task.ui.theme.bold


@Composable
fun UsersListScreen(
    viewModel: UsersListViewModel = hiltViewModel(),
    onNavigateToUserDetails: () -> Unit,
    onBackClicked: () -> Unit
) {

    val uiState = viewModel.uiStateFlow.collectAsStateWithLifecycle().value

    MainTopBar(
        title = R.string.users,
        isPullRefresh = false,
        leftIcon = R.drawable.ic_vector_back_arrow,
        onLeftIconClicked = onBackClicked,
        content = {
            when {
                uiState.appError != null || uiState.userUiModel.isEmpty() -> FailureView(
                    tapText = R.string.tap_to_load_content,
                    icon = R.drawable.ic_vector_error,
                    onTapToRefresh = viewModel::refresh
                )

                else -> UsersListContent(
                    userUiModelList = uiState.userUiModel,
                    onItemClicked = { user ->
                        onNavigateToUserDetails()
                    }
                )
            }
        })

}

@Composable
fun UsersListContent(
    userUiModelList: List<UserUiModel>,
    onItemClicked: (UserUiModel) -> Unit
) {
    GeneralLazyColumn(
        modifier = Modifier.padding(all = 8.dp),
        list = userUiModelList
    ) { user ->
        UserItem(
            name = user.name ?: "-",
            age = user.age.toString(),
            jobTitle = user.jobTitle ?: "-",
            genderType = user.genderType ?: "-",
            onClicked = { onItemClicked(user) }
        )
    }
}

@Composable
fun UserItem(
    modifier: Modifier = Modifier,
    name: String,
    age: String,
    jobTitle: String,
    genderType: String,
    onClicked: () -> Unit = {},
) {
    Surface(
        modifier = modifier.noRippleClickable(onClick = onClicked),
        color = NaturalLight,
        shape = RoundedCornerShape(16.dp)
    ) {

        Column(
            modifier = modifier
                .padding(all = 16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(modifier = modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = R.string.name_with_colon),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium.bold.copy(color = Primary),
                )

                Spacer(modifier = modifier.width(8.dp))

                Text(
                    text = name,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium.copy(color = Primary),
                )
            }


            Spacer(modifier = modifier.height(8.dp))

            Row(modifier = modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = R.string.age_with_colon),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium.bold.copy(color = Primary),
                )

                Spacer(modifier = modifier.width(8.dp))

                Text(
                    text = stringResource(id = R.string.years_place_holder, age),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium.copy(color = Primary),
                )
            }


            Spacer(modifier = modifier.height(8.dp))


            Row(modifier = modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = R.string.job_title_with_colon),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium.bold.copy(color = Primary),
                )

                Spacer(modifier = modifier.width(8.dp))

                Text(
                    text = jobTitle,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium.copy(color = Primary),
                )
            }

            Spacer(modifier = modifier.height(8.dp))

            Row(modifier = modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = R.string.gender_type),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium.bold.copy(color = Primary),
                )

                Spacer(modifier = modifier.width(8.dp))

                Text(
                    text = genderType,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium.copy(color = Primary),
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun OrderItemPreview() {
    UserItem(
        name = "Mohamed Keshawy",
        age = "38",
        jobTitle = "Senior Android Developer",
        genderType = "Male",
        onClicked = {}
    )
}