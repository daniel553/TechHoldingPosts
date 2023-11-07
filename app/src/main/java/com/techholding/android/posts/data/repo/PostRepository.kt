package com.techholding.android.posts.data.repo

import com.techholding.android.posts.data.api.comment.ICommentService
import com.techholding.android.posts.data.api.post.IPostService
import javax.inject.Inject


interface IPostRepository {

    /**
     * Gets all post from network
     */
    suspend fun fetchAllPostFromApi()

    /**
     * Gets all comments for id
     */
    suspend fun fetchPostCommentsById(postId: Long)
}

class PostRepository @Inject constructor(
    private val service: IPostService,
    private val commentService: ICommentService
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

    override suspend fun fetchPostCommentsById(postId: Long) {
        commentService.getAllCommentsForPost(postId).onSuccess {
            //Store comments on post
        }.onFailure {
            //Log issue
        }
    }
}