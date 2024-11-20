package com.jk.gogit

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight


interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

@Composable
expect fun getIconResource(): Painter


