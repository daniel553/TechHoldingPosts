package com.techholding.android.posts.data.api.post

import com.techholding.android.posts.data.api.model.PostResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface IPostService {
    /**
     * Fetch all post of response
     */
    suspend fun getAllPosts(): Result<List<PostResponse>?>

}

class PostService @Inject constructor(
    private val postApi: PostApi
) : IPostService {

    override suspend fun getAllPosts(): Result<List<PostResponse>?> {
        return withContext(Dispatchers.IO) {
            val response = postApi.getAllPosts()
            if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                Result.failure(Exception("Can not get all posts - error:${response.code()}"))
            }
        }
    }
}