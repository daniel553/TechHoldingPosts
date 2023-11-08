package com.techholding.android.posts.domain

import com.techholding.android.posts.data.repo.IPostRepository
import com.techholding.android.posts.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SubscribeToAllPostsUseCase @Inject constructor(
    private val postRepository: IPostRepository
) {
    suspend operator fun invoke(): Flow<List<Post>> {
        return withContext(Dispatchers.IO) { postRepository.postsFromDb().map { it.toPost() } }
    }
}
