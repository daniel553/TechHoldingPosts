package com.techholding.android.posts.domain

import com.techholding.android.posts.data.repo.IPostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubscribeToAllPostsUseCase @Inject constructor(
    private val postRepository: IPostRepository
) {

}