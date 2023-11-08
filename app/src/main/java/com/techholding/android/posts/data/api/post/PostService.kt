package com.techholding.android.posts.data.api.post

import com.techholding.android.posts.data.api.model.PostResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

interface IPostService {
    /**
     * Fetch all post of response
     */
    suspend fun getAllPosts(): Result<List<PostResponse>?>

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
                    Result.success(posts.body())
                } else {
                    Result.failure(Exception("Can not get all posts"))
                }
            } else {
                Result.failure(Exception("Can not get all posts from network"))
            }
        }
    }
}