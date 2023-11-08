package com.techholding.android.posts.data.repo

import com.techholding.android.posts.data.api.comment.ICommentService
import com.techholding.android.posts.data.api.post.IPostService
import com.techholding.android.posts.data.db.post.PostDAO
import com.techholding.android.posts.data.db.post.PostEntity
import com.techholding.android.posts.data.toEntityList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import timber.log.Timber
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

    suspend fun postsFromDb(): Flow<List<PostEntity>>
}

@Singleton
class PostRepository @Inject constructor(
    private val service: IPostService,
    private val commentService: ICommentService,
    private val postDao: PostDAO
) : IPostRepository {

    override suspend fun fetchAllPostFromApi(): Boolean {
        return !service.getAllPosts().onSuccess { posts ->
            //Place to store data
            posts?.let {
                Timber.d("fetchAllPostFromApi success: ${it.count()} posts")
                postDao.insertMany(it.toEntityList())
            }
        }.onFailure {
            Timber.d("fetchAllPostFromApi fail: ${it.message}")
        }.isSuccess
    }

    override suspend fun fetchPostCommentsById(postId: Long) {
        commentService.getAllCommentsForPost(postId).onSuccess {
            //Store comments on post
        }.onFailure {
            //Log issue
        }
    }

    override suspend fun postsFromDb(): Flow<List<PostEntity>> = flow() {
        emit(postDao.getAll())
    }

}

