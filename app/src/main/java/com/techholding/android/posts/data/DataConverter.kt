package com.techholding.android.posts.data

import com.techholding.android.posts.data.api.model.PostResponse
import com.techholding.android.posts.data.db.post.PostEntity

fun List<PostResponse>.toEntityList(): List<PostEntity> {
    return this.map { it.toPostEntity() }
}

fun PostResponse.toPostEntity(): PostEntity {
    return PostEntity(
        id = this.id,
        title = this.title,
        body = this.body,
        userId = this.userId
    )
}
