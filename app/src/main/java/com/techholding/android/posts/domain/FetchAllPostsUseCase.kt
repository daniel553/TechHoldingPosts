package com.techholding.android.posts.domain

import com.techholding.android.posts.data.repo.IPostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchAllPostsUseCase @Inject constructor(
    private val postRepository: IPostRepository
) {
    suspend operator fun invoke(): Boolean {
        return postRepository.fetchAllPostFromApi()
    }
}