package com.techholding.android.posts.domain

import com.techholding.android.posts.data.repo.IPostRepository
import com.techholding.android.posts.model.Post
import javax.inject.Inject

class CreatePostUseCase @Inject constructor(
    private val postRepository: IPostRepository
) {
    suspend operator fun invoke(post: Post): Long {
        return postRepository.postToNetwork(post)
    }
}