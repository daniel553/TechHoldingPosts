package com.techholding.android.posts.data.repo

import com.techholding.android.posts.data.api.post.IPostService
import javax.inject.Inject


interface IPostRepository {

    /**
     * Gets all post from network
     */
    suspend fun fetchAllPostFromApi()
}

class PostRepository @Inject constructor(
    private val service: IPostService
) : IPostRepository {

    override suspend fun fetchAllPostFromApi() {
        service.getAllPosts().onSuccess { posts ->
            //Place to store data
            posts?.let {

            }
        }.onFailure {
            //Log issue
        }
    }
}