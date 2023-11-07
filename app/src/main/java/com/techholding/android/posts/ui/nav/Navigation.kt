package com.techholding.android.posts.ui.nav

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.techholding.android.posts.ui.view.post.PostListScreen
import com.techholding.android.posts.ui.view.post.PostListViewModel

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Router.PostListScreen.path) {
        composable(Router.PostListScreen.path) {
            PostListScreen(
                navController = navController,
                viewModel = hiltViewModel<PostListViewModel>()
            )
        }
    }
}