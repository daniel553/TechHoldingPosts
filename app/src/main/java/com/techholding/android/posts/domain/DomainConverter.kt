package com.techholding.android.posts.domain

import com.techholding.android.posts.data.db.comment.CommentEntity
import com.techholding.android.posts.data.db.post.PostEntity
import com.techholding.android.posts.model.Comment
import com.techholding.android.posts.model.Post

fun List<PostEntity>.toPost(): List<Post> {
    return this.map { it ->
        Post(
            id = it.id,
            title = it.title,
            body = it.body,
            userId = it.userId,
            comments = it.comments?.map { c -> c.toComment() } ?: emptyList()
        )
    }
}

fun PostEntity.toPost(): Post {
    return Post(
        id = this.id,
        title = this.title,
        body = this.body,
        userId = this.userId,
        comments = this.comments?.map { it.toComment() } ?: emptyList()
    )
}

fun CommentEntity.toComment(): Comment {
    return Comment(
        id = this.id,
        name = this.name,
        email = this.email,
        body = this.body,
        postId = this.postId
    )
}