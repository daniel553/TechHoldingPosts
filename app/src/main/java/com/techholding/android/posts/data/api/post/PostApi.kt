package com.techholding.android.posts.data.api.post

import com.techholding.android.posts.data.api.model.PostRequest
import com.techholding.android.posts.data.api.model.PostResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface PostApi {
    @GET("/posts")
    suspend fun getAllPosts(): Response<List<PostResponse>>

    @Headers("Content-type: application/json; charset=UTF-8")
    @POST("/posts")
    suspend fun postPost(@Body post: PostRequest): Response<PostResponse>
}