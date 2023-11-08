package com.techholding.android.posts.data.api.comment

import com.techholding.android.posts.data.api.model.CommentResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

interface ICommentService {
    suspend fun getAllCommentsForPost(postId: Long): Result<List<CommentResponse>>
}

@Singleton
class CommentService @Inject constructor(
    private val commentApi: CommentApi
) : ICommentService {
    override suspend fun getAllCommentsForPost(postId: Long): Result<List<CommentResponse>> {
        return withContext(Dispatchers.IO) {
            val comments = runCatching {
                commentApi.getCommentsByPostId(postId)
            }.getOrNull()

            if (comments != null) {
                if (comments.isSuccessful) {
                    Result.success(comments.body()!!)
                } else {
                    Result.failure(Exception("Get all comments response error"))
                }
            } else {
                Result.failure(Exception("Can not get comments from network"))
            }
        }
    }
}