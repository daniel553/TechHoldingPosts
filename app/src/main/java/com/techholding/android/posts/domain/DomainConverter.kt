package com.techholding.android.posts.domain

import com.techholding.android.posts.data.db.post.PostEntity
import com.techholding.android.posts.model.Post

fun List<PostEntity>.toPost(): List<Post> {
    return this.map {
        Post(
            id = it.id,
            title = it.title,
            body = it.body,
            userId = it.userId
        )
    }
}