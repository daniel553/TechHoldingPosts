package com.techholding.android.posts.data.api.comment

import com.techholding.android.posts.data.api.model.CommentResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface ICommentService {
    suspend fun getAllCommentsForPost(postId: Long): Result<List<CommentResponse>>
}

class CommentService @Inject constructor(
    private val commentApi: CommentApi
) : ICommentService {
    override suspend fun getAllCommentsForPost(postId: Long): Result<List<CommentResponse>> {
        return withContext(Dispatchers.IO) {
            val response = commentApi.getCommentsByPostId(postId)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Get all comments response error - code:${response.code()}"))
            }
        }
    }
}