package com.madarsoft_task.features.common.composable

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.madarsoft_task.ui.theme.Primary
import com.madarsoft_task.ui.theme.semiBold


@Composable
fun UserPopUpContent(
    name: String,
    @DrawableRes icon: Int,
    @StringRes titleRes: Int,
    @StringRes leftBtnRes: Int,
    @StringRes rightBtnRes: Int,
    onLeftBtnClicked: () -> Unit,
    onRightBtnClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .width(100.dp)
                .height(100.dp),
            painter = painterResource(id = icon),
            tint = Primary,
            contentDescription = "Icon"
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = stringResource(id = titleRes, name),
            fontSize = 16.sp,
            style = MaterialTheme.typography.bodyMedium.semiBold.copy(color = Primary)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 25.dp)
        ) {

            PrimaryButton(
                modifier = Modifier.weight(1f),
                onClick = onLeftBtnClicked
            ) {
                Text(
                    stringResource(id = leftBtnRes),
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.bodyLarge.semiBold,
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            AppOutlinedButton(
                onClick = onRightBtnClicked,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    stringResource(id = rightBtnRes),
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.bodyLarge.semiBold.copy(color = Primary),
                )
            }
        }
    }
}