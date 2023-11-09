package com.techholding.android.posts.data.api.post

import com.techholding.android.posts.data.api.model.PostRequest
import com.techholding.android.posts.data.api.model.PostResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

interface IPostService {
    /**
     * Fetch all post of response
     */
    suspend fun getAllPosts(): Result<List<PostResponse>?>

    /**
     * Create a new post to network request
     * @post with no id.
     */
    suspend fun createPost(post: PostRequest): Result<PostResponse>

}

@Singleton
class PostService @Inject constructor(
    private val postApi: PostApi
) : IPostService {

    override suspend fun getAllPosts(): Result<List<PostResponse>?> {
        return withContext(Dispatchers.IO) {
            val posts = runCatching {
                postApi.getAllPosts()
            }.getOrNull()

            if (posts != null) {
                if (posts.isSuccessful) {
                    Result.success(posts.body()!!)
                } else {
                    Result.failure(Exception("Can not get all posts"))
                }
            } else {
                Result.failure(Exception("Can not get all posts from network"))
            }
        }
    }

    override suspend fun createPost(post: PostRequest): Result<PostResponse> {
        return withContext(Dispatchers.IO) {
            val posts = runCatching {
                postApi.postPost(post)
            }.getOrNull()

            if (posts != null) {
                if (posts.isSuccessful) {
                    Result.success(posts.body()!!)
                } else {
                    Result.failure(Exception("Can not get all posts"))
                }
            } else {
                Result.failure(Exception("Can not get all posts from network"))
            }
        }
    }
}