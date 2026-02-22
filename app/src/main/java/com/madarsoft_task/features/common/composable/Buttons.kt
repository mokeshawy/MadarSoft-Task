package com.madarsoft_task.features.common.composable

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: RoundedCornerShape = RoundedCornerShape(30.dp),
    colors: ButtonColors = primaryButtonColors(),
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = 45.dp),
        enabled = enabled,
        content = content,
        colors = colors,
        shape = shape,
    )
}


@Composable
fun SecondaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = 45.dp),
        enabled = enabled,
        content = content,
        colors = secondaryButtonColors()
    )
}


@Composable
fun primaryButtonColors() = ButtonDefaults.buttonColors(
    disabledContainerColor = Color(0xFFE6E6ED),
    disabledContentColor = Color(0xFFB0B0C6)
)


@Composable
fun secondaryButtonColors() = ButtonDefaults.buttonColors(
    containerColor = Color(0xFF366EE3),
    contentColor = Color(0xFFFFFFFF),
    disabledContainerColor = Color(0xFFEBF1FC),
    disabledContentColor = Color(0xFFC1D2F6)
)