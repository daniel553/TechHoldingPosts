package com.techholding.android.posts.model

data class Comment(
    val id: Long,
    val name: String,
    val email: String,
    val body: String,
    val postId: Long,
)
