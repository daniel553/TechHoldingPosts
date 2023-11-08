package com.techholding.android.posts.ui.nav

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.techholding.android.posts.ui.view.post.PostListScreen
import com.techholding.android.posts.ui.view.post.PostListViewModel
import com.techholding.android.posts.ui.view.post.details.PostDetailsScreen
import com.techholding.android.posts.ui.view.post.details.PostDetailsViewModel

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
        composable(
            route = Router.PostDetailsScreen.path,
            arguments = listOf(navArgument(Router.ID) { type = NavType.LongType })
        ) { stack ->
            val viewModel = hiltViewModel<PostDetailsViewModel>()
            val id = stack.arguments?.getLong(Router.ID)
            viewModel.setId(id)
            PostDetailsScreen(navController = navController, viewModel = viewModel)
        }
    }
}