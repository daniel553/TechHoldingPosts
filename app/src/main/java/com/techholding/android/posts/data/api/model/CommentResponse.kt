package com.techholding.android.posts.data.api.model

/**
 * Comment response entity, expecting to have the following format:
 *  <code>
 *   {
 *     "postId": 1,
 *     "id": 1,
 *     "name": "id labore ...",
 *     "email": "Eliseo@gardner.biz",
 *     "body": "laudantium ..."
 *   },
 *  </code>
 */
data class CommentResponse(
    val postId: Long,
    val id: Long,
    val name: String,
    val email: String,
    val body: String
)
