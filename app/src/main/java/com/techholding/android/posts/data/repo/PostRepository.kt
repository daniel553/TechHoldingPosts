package com.techholding.android.posts.data.repo

import com.techholding.android.posts.data.api.comment.ICommentService
import com.techholding.android.posts.data.api.post.IPostService
import javax.inject.Inject
import javax.inject.Singleton


interface IPostRepository {

    /**
     * Gets all post from network
     */
    suspend fun fetchAllPostFromApi(): Boolean

    /**
     * Gets all comments for id
     */
    suspend fun fetchPostCommentsById(postId: Long)
}

@Singleton
class PostRepository @Inject constructor(
    private val service: IPostService,
    private val commentService: ICommentService
) : IPostRepository {

    override suspend fun fetchAllPostFromApi(): Boolean {
        val posts = service.getAllPosts().onSuccess { posts ->
            //Place to store data
            posts?.let {

            }
        }.onFailure {
            //Log issue
        }.getOrThrow()
        return posts != null
    }

    override suspend fun fetchPostCommentsById(postId: Long) {
        commentService.getAllCommentsForPost(postId).onSuccess {
            //Store comments on post
        }.onFailure {
            //Log issue
        }
    }
}