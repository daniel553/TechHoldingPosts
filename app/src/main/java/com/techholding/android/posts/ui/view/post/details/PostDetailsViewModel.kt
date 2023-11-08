package com.techholding.android.posts.ui.view.post.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techholding.android.posts.domain.FetchPostDetailsUseCase
import com.techholding.android.posts.domain.SubscribeToPostsUseCase
import com.techholding.android.posts.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(
    private val fetchPostDetailsUseCase: FetchPostDetailsUseCase,
    private val subscribeToPostsUseCase: SubscribeToPostsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PostDetailUiState())
    val uiState = _uiState.asStateFlow()

    fun setId(id: Long?) {
        id?.let { postId ->
            _uiState.update { it.copy(id = postId) }
            getPostDetails()
        }
    }

    private fun getPostDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            subscribeToPost()
            fetchPostDetailsUseCase(_uiState.value.id)
        }
    }

    private suspend fun subscribeToPost() {
        subscribeToPostsUseCase(_uiState.value.id).onEach { post ->
            _uiState.update { it.copy(post = post) }
        }.stateIn(viewModelScope, SharingStarted.Eagerly, null)
    }

}

data class PostDetailUiState(
    val id: Long = 0L,
    val loading: Boolean = false,
    val post: Post? = null
)