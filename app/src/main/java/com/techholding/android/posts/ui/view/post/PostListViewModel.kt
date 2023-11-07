package com.techholding.android.posts.ui.view.post

import androidx.lifecycle.ViewModel
import com.techholding.android.posts.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(

) : ViewModel() {
    private val _uiState = MutableStateFlow(PostListUiState())
    val uiState = _uiState.asStateFlow()
}

data class PostListUiState(
    val loading: Boolean = false,
    val posts: List<Post> = emptyList()
)