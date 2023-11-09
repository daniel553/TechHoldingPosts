package com.techholding.android.posts.domain

import com.techholding.android.posts.data.repo.IPostRepository
import com.techholding.android.posts.model.Post
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

internal class CreatePostUseCaseTest {

    private lateinit var useCase: CreatePostUseCase

    @MockK(relaxed = true)
    lateinit var postRepository: IPostRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = CreatePostUseCase(postRepository)
    }

    @Test
    fun `Given post when creating to network then verify post to network invoked`() = runTest {
        val post = mockk<Post>()
        useCase.invoke(post)
        coVerify { postRepository.postToNetwork(post) }
    }
}