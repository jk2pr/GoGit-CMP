package com.jk.gogit.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jk.gogit.localproviders.LocalSnackBarHostState


@Composable
fun Page(
    title: @Composable () -> Unit = {},
    menuItems: List<DropdownMenuItemContent> = emptyList(),
    floatingActionButton: @Composable () -> Unit = {},
    contentAlignment: Alignment = Alignment.Center,
    bottomBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = { AppBar(menuItems = menuItems, title = title) },
        floatingActionButton = floatingActionButton,
        bottomBar = bottomBar,
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = contentAlignment
            ) {
                content()
            }
        },
        snackbarHost = {
            SnackbarHost(
                hostState = LocalSnackBarHostState.current,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(Alignment.Bottom)
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(menuItems: List<DropdownMenuItemContent>, title: @Composable () -> Unit) {

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(),
        title = title,
        navigationIcon = {
            // val navController = LocalNavController.current
            //   val isRootScreen = navController.previousBackStackEntry == null
            //     if (!isRootScreen) NavigationIcon(navController = navController)
        },

        actions = {

            menuItems.forEach {
                it.menu()
                Icon(
                    imageVector = Icons.Outlined.MoreVert,
                    contentDescription = "OverFlow",
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .align(Alignment.CenterVertically)
                )

            }
        }
    )
}
/*
@Composable
private fun NavigationIcon(navController: NavController) {

    Icon(
        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
        contentDescription = "",
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .clickable {
                navController.popBackStack()
            }
    )
}*/

data class DropdownMenuItemContent(var menu: @Composable () -> Unit)
