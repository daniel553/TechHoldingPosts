package com.techholding.android.posts.ui.view.post.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techholding.android.posts.domain.FetchAllPostsUseCase
import com.techholding.android.posts.domain.SubscribeToAllPostsUseCase
import com.techholding.android.posts.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostCreateViewModel @Inject constructor(
    private val fetchAllPostsUseCase: FetchAllPostsUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(PostCreateUiState())
    val uiState = _uiState.asStateFlow()

    fun updatePost(title: String, body: String) {
        _uiState.update { state ->
            state.copy(
                post = state.post.copy(
                    title = title,
                    body = body
                )
            )
        }
    }

    fun doPost() {
        viewModelScope.launch {
        }
    }
}

data class PostCreateUiState(
    val loading: Boolean = false,
    val post: Post = Post(0L, "", "", 1L),
)