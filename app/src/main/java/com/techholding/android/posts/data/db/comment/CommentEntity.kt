package com.techholding.android.posts.data.db.comment

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "comment_entity",
    primaryKeys = ["id", "postId"]
)
data class CommentEntity(
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "body")
    val body: String,
    @ColumnInfo(name = "postId")
    val postId: Long,
)