package com.techholding.android.posts.ui.view.post.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techholding.android.posts.domain.CreatePostUseCase
import com.techholding.android.posts.domain.FetchAllPostsUseCase
import com.techholding.android.posts.domain.SubscribeToAllPostsUseCase
import com.techholding.android.posts.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class PostCreateViewModel @Inject constructor(
    private val createPostUseCase: CreatePostUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(PostCreateUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<PostCreateEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

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
            val post = uiState.value.post.copy()
            _uiState.update { state -> state.copy(loading = true) }
            val posted = createPostUseCase(post)
            if (posted > 0) {
                //Successful creation
                delay(2.seconds)
                _uiEvent.emit(PostCreateEvent.BackToList(posted))
            } else {
                _uiState.update { state ->
                    state.copy(
                        error = true,
                        loading = false
                    )
                }
            }
        }
    }
}

data class PostCreateUiState(
    val loading: Boolean = false,
    val error: Boolean = false,
    val post: Post = Post(0L, "", "", 1L),
)

sealed interface PostCreateEvent {
    data class BackToList(val id: Long) : PostCreateEvent
}