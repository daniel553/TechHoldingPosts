package com.techholding.android.posts.data.api.model

/**
 * Post entity, expecting to have the following format
 * <code>
 *   {
 *     "userId": 1,
 *     "id": 1,
 *     "title": "sunt ...",
 *     "body": "quia..."
 *   },
 *  </code>
 */
data class PostResponse(
    val userId: Long,
    val id: Long,
    val title: String,
    val body: String,
)
