package com.jk.gogit.localproviders

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.staticCompositionLocalOf


val LocalSnackBarHostState = staticCompositionLocalOf<SnackbarHostState> {
    error("No LocalScaffold provided")
}