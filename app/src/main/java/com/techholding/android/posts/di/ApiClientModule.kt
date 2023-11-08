package com.techholding.android.posts.di

import com.techholding.android.posts.data.api.comment.CommentApi
import com.techholding.android.posts.data.api.comment.CommentService
import com.techholding.android.posts.data.api.comment.ICommentService
import com.techholding.android.posts.data.api.post.IPostService
import com.techholding.android.posts.data.api.post.PostApi
import com.techholding.android.posts.data.api.post.PostService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * General API client module, installed on singleton component
 */
@Module
@InstallIn(SingletonComponent::class)
object ApiClientModule {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

/**
 * Retrofit
 */
@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {
    @Provides
    @Singleton
    fun providePostApi(retrofit: Retrofit): PostApi = retrofit.create(PostApi::class.java)

    @Provides
    @Singleton
    fun provideCommentApi(retrofit: Retrofit): CommentApi = retrofit.create(CommentApi::class.java)
}


/**
 * Service module.
 */
@Module
@InstallIn(SingletonComponent::class)
interface ServiceModule {
    @Binds
    fun bindPostService(postService: PostService): IPostService

    @Binds
    fun bindCommentService(commentService: CommentService): ICommentService
}