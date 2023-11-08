package com.techholding.android.posts.di

import android.content.Context
import androidx.room.Room
import com.techholding.android.posts.data.db.AppDatabase
import com.techholding.android.posts.data.db.comment.CommentDAO
import com.techholding.android.posts.data.db.post.PostDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Database and data access modules
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME).build()
    }

    @Provides
    @Singleton
    fun providePostDao(db: AppDatabase): PostDAO = db.postDao()

    @Provides
    @Singleton
    fun provideCommentDao(db: AppDatabase): CommentDAO = db.commentDao()

}