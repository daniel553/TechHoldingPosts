package com.techholding.android.posts.ui.view.post

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.techholding.android.posts.R
import com.techholding.android.posts.ui.nav.Router

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostListScreen(navController: NavController, viewModel: PostListViewModel) {
    val postListUiState by viewModel.uiState.collectAsState()
    LaunchedEffect(true) {
        viewModel.getAllPosts()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(text = stringResource(id = R.string.post_list_title).uppercase())
                })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Router.PostCreateScreen.path)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        with(postListUiState) {
            when {
                loading -> PostListShimmerView(modifier = Modifier.padding(padding))
                !loading && posts.isNotEmpty() -> PostListView(
                    posts = posts,
                    scrollToIndex = selectedIndex,
                    modifier = Modifier.padding(padding),
                    onPostSelected = { postId ->
                        navController.navigate(
                            route = Router.PostDetailsScreen.buildRoute(postId.toString())
                        )
                    }
                )
            }
        }
    }

}