package com.techholding.android.posts.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.techholding.android.posts.data.db.comment.CommentDAO
import com.techholding.android.posts.data.db.comment.CommentEntity
import com.techholding.android.posts.data.db.post.PostDAO
import com.techholding.android.posts.data.db.post.PostEntity

/**
 * General Database, some entities defined: Post, Comments,
 * add as relation needed, ie: User, or photos, etc.
 */
@Database(entities = [PostEntity::class, CommentEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "techholding_posts.db"
    }

    abstract fun postDao(): PostDAO
    abstract fun commentDao(): CommentDAO
}