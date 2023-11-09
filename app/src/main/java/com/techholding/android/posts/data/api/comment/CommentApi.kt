package com.techholding.android.posts.data.api.comment

import com.techholding.android.posts.data.api.model.CommentResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CommentApi {
    @GET("/posts/{id}/comments")
    suspend fun getCommentsByPostId(@Path("id") id: Long): Response<List<CommentResponse>>
}