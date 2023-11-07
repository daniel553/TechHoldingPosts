package com.techholding.android.posts.ui.view.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techholding.android.posts.domain.FetchAllPostsUseCase
import com.techholding.android.posts.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val fetchAllPostsUseCase: FetchAllPostsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(PostListUiState())
    val uiState = _uiState.asStateFlow()

    fun getAllPosts() {
        viewModelScope.launch {
            _uiState.update { state -> state.copy(loading = true) }
            val fetched = fetchAllPostsUseCase()
            _uiState.update { state -> state.copy(loading = false) }
        }
    }
}

data class PostListUiState(
    val loading: Boolean = false,
    val posts: List<Post> = emptyList()
)