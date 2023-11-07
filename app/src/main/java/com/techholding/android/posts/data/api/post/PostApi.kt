package com.techholding.android.posts.data.api.post

import com.techholding.android.posts.data.api.model.PostResponse
import retrofit2.Response
import retrofit2.http.GET

interface PostApi {
    @GET("/posts")
    suspend fun getAllPosts(): Response<List<PostResponse>>
}