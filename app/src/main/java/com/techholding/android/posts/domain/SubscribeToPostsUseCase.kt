package com.techholding.android.posts.domain

import com.techholding.android.posts.data.db.comment.CommentEntity
import com.techholding.android.posts.data.db.post.PostEntity
import com.techholding.android.posts.data.repo.IPostRepository
import com.techholding.android.posts.model.Comment
import com.techholding.android.posts.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SubscribeToPostsUseCase @Inject constructor(
    private val postRepository: IPostRepository
) {
    suspend operator fun invoke(postId: Long): Flow<Post> {
        return withContext(Dispatchers.IO) {
            postRepository.postWithComments(postId).map { it.toPost() }
        }
    }
}
