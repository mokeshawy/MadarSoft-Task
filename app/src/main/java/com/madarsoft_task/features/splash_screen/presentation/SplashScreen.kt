package com.madarsoft_task.features.splash_screen.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.ui_component.subcompose_async.SubcomposeAsyncImageComponent
import com.madarsoft_task.R
import com.madarsoft_task.ui.theme.OnPrimary
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onNavigateToAddNewUserScreen: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = OnPrimary),
        contentAlignment = Alignment.Center
    ) {
        SubcomposeAsyncImageComponent(
            modifier = modifier.size(250.dp),
            imageUrl = R.drawable.ic_vector_contact_calendar,
            errorPlaceholder = R.drawable.ic_vector_placeholder
        )
    }

    LaunchedEffect(Unit) {
        delay(timeMillis = 1000)
        onNavigateToAddNewUserScreen()
    }
}

@Composable
@Preview(showBackground = true)
fun SplashScreenPreview() {
    SplashScreen(onNavigateToAddNewUserScreen = {})
}
