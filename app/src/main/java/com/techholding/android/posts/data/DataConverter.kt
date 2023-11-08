package com.techholding.android.posts.data

import com.techholding.android.posts.data.api.model.CommentResponse
import com.techholding.android.posts.data.api.model.PostResponse
import com.techholding.android.posts.data.db.comment.CommentEntity
import com.techholding.android.posts.data.db.post.PostEntity

fun List<PostResponse>.toEntityList(): List<PostEntity> {
    return this.map { it.toPostEntity() }
}

fun PostResponse.toPostEntity(): PostEntity {
    return PostEntity(
        id = this.id,
        title = this.title,
        body = this.body,
        userId = this.userId,
    )
}


fun List<CommentResponse>.toCommentEntityList(): List<CommentEntity> {
    return this.map { it.toCommentEntity() }
}

fun CommentResponse.toCommentEntity(): CommentEntity {
    return CommentEntity(
        id = this.id,
        name = this.name,
        email = this.email,
        body = this.body,
        postId = this.postId
    )
}
