package com.techholding.android.posts.ui.view.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techholding.android.posts.domain.FetchAllPostsUseCase
import com.techholding.android.posts.domain.SubscribeToAllPostsUseCase
import com.techholding.android.posts.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val fetchAllPostsUseCase: FetchAllPostsUseCase,
    private val subscribeToAllPostsUseCase: SubscribeToAllPostsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(PostListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            subscribe()
        }
    }

    fun getAllPosts() {
        viewModelScope.launch {
            _uiState.update { state -> state.copy(loading = true) }
            val fetched = fetchAllPostsUseCase()
            subscribe()
            _uiState.update { state -> state.copy(loading = false, fetched = fetched) }
        }
    }

    private suspend fun subscribe() {
        subscribeToAllPostsUseCase()
            .onEach { posts ->
                val updated = _uiState.updateAndGet { state ->
                    state.copy(posts = posts, selectedIndex = -1)
                }
                if (updated.selected > 0) {
                    val selectedIndex =
                        updated.posts.indexOf(updated.posts.find { updated.selected == it.id })
                    if (selectedIndex >= 0)
                        _uiState.update { state -> state.copy(selectedIndex = selectedIndex) }
                }
            }.stateIn(viewModelScope, started = SharingStarted.Eagerly, 5_000)
    }

    fun setId(id: Long) {
        if (id != 0L) {
            _uiState.update { state -> state.copy(selected = id) }
        }
    }

}

data class PostListUiState(
    val loading: Boolean = false,
    val posts: List<Post> = emptyList(),
    val fetched: Boolean = false,
    val selected: Long = 0L,
    val selectedIndex: Int = -1
)