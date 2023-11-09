package com.techholding.android.posts.data.db.post

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.techholding.android.posts.data.db.comment.CommentEntity

@Entity(tableName = "post_entity")
data class PostEntity @JvmOverloads constructor(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long = 0L,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "body")
    val body: String,
    @ColumnInfo(name = "userId")
    val userId: Long,
) {
    @Ignore
    var comments: List<CommentEntity>? = emptyList()
}