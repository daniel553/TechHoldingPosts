package com.techholding.android.posts.domain

import com.techholding.android.posts.data.repo.IPostRepository
import javax.inject.Inject

class FetchPostDetailsUseCase @Inject constructor(
    private val postRepository: IPostRepository
) {
    suspend operator fun invoke(id: Long): Boolean {
        return postRepository.fetchPostCommentsById(id)
    }
}