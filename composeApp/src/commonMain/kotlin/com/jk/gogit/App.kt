package com.jk.gogit

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import com.jk.gogit.localproviders.LocalSnackBarHostState
import com.jk.gogit.login.screen.LoginScreen
import com.jk.gogit.ui.GoGitTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    GoGitTheme {
        CompositionLocalProvider(
            LocalSnackBarHostState provides remember { SnackbarHostState() },
        ) {
            LoginScreen()
        }
    }
}