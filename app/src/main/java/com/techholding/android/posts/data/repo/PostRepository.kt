package com.techholding.android.posts.data.repo

import com.techholding.android.posts.data.api.comment.ICommentService
import com.techholding.android.posts.data.api.post.IPostService
import com.techholding.android.posts.data.db.comment.CommentDAO
import com.techholding.android.posts.data.db.post.PostDAO
import com.techholding.android.posts.data.db.post.PostEntity
import com.techholding.android.posts.data.toCommentEntityList
import com.techholding.android.posts.data.toEntityList
import com.techholding.android.posts.data.toPostEntity
import com.techholding.android.posts.data.toPostRequest
import com.techholding.android.posts.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
    suspend fun fetchPostCommentsById(postId: Long): Boolean

    /**
     * From cache stored in db
     */
    suspend fun postsFromDb(): Flow<List<PostEntity>>

    /**
     * From cache stored in db
     */
    suspend fun postWithComments(postId: Long): Flow<PostEntity>

    /**
     * Do a post of post
     */
    suspend fun postToNetwork(post: Post): Long
}

@Singleton
class PostRepository @Inject constructor(
    private val service: IPostService,
    private val commentService: ICommentService,
    private val postDao: PostDAO,
    private val commentsDao: CommentDAO
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

    override suspend fun fetchPostCommentsById(postId: Long): Boolean {
        return commentService.getAllCommentsForPost(postId).onSuccess { comments ->
            //Store comments on post
            comments.let {
                Timber.d("fetchPostCommentsById success ${comments.count()}")
                commentsDao.insertMany(it.toCommentEntityList())
            }
        }.onFailure {
            Timber.d("fetchPostCommentsById failed ${it.message}")
        }.isSuccess
    }

    override suspend fun postsFromDb(): Flow<List<PostEntity>> = flow() {
        emit(postDao.getAll())
    }

    override suspend fun postWithComments(postId: Long): Flow<PostEntity> = flow {
        val post = postDao.get(postId)
        emit(post)
        val postComments = commentsDao.getByPostId(postId)
        emit(post.apply { comments = postComments })
    }

    override suspend fun postToNetwork(post: Post): Long {
        val res = service.createPost(post.toPostRequest()).onSuccess { postResponse ->
            //Store posted post
            postResponse.let {
                Timber.d("postToNetwork success $it")
                postDao.insert(it.toPostEntity())
            }
        }.onFailure {
            Timber.d("postToNetwork failed ${it.message}")
        }.getOrNull()
        return res?.id ?: 0L
    }

}

