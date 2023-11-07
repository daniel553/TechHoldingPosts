package com.techholding.android.posts.di

import com.techholding.android.posts.data.repo.IPostRepository
import com.techholding.android.posts.data.repo.PostRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Repositories module.
 */
@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    fun bindPostRepository(postRepository: PostRepository): IPostRepository
}