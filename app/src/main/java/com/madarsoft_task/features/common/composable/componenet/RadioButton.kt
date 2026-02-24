package com.madarsoft_task.features.common.composable.componenet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.madarsoft_task.ui.theme.NaturalLightActive
import com.madarsoft_task.ui.theme.Primary
import com.madarsoft_task.ui.theme.Secondary
import com.madarsoft_task.ui.theme.regular

@Composable
fun RadioButtonWithText(
    text: String,
    selectedOption: String?,
    onOptionSelected: (String) -> Unit,
) {
    Row(
        modifier = Modifier.clickable { onOptionSelected(text) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selectedOption == text,
            onClick = { onOptionSelected(text) },
            colors = RadioButtonDefaults.colors(
                selectedColor = Secondary, unselectedColor = NaturalLightActive
            )
        )
        Text(
            text = text,
            fontSize = 16.sp,
            style = MaterialTheme.typography.titleMedium.regular.copy(color = Primary)
        )
    }
}