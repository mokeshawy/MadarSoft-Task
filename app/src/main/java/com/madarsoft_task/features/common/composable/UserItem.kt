package com.madarsoft_task.features.common.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.core.ui_component.ui_extensions.noRippleClickable
import com.madarsoft_task.R
import com.madarsoft_task.ui.theme.NaturalLight
import com.madarsoft_task.ui.theme.Primary
import com.madarsoft_task.ui.theme.bold

@Composable
fun UserItem(
    modifier: Modifier = Modifier,
    name: String,
    age: String,
    jobTitle: String,
    genderType: String,
    isShowDeleteIcon: Boolean = true,
    onClicked: () -> Unit = {},
    onDeleteUser: () -> Unit = {}
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

            if (isShowDeleteIcon) {
                Icon(
                    modifier = modifier
                        .align(Alignment.End)
                        .noRippleClickable(onClick = onDeleteUser),
                    painter = painterResource(id = R.drawable.ic_vector_delete),
                    contentDescription = "Delete Icon"
                )
            }
        }
    }
}