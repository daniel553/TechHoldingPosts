package com.techholding.android.posts.domain

import com.techholding.android.posts.data.db.post.PostEntity
import com.techholding.android.posts.model.Post
import io.mockk.InternalPlatformDsl.toArray
import org.junit.Assert
import org.junit.Test

internal class DomainConverterTest {

    @Test
    fun `Given a post entity when converting then verify data converted is the same`() {
        val post = PostEntity(0L, "title", "body", 0L)
        val expected = Post(0L, "title", "body", 0L)

        Assert.assertEquals(expected, post.toPost())
    }

    @Test
    fun `Given a list of post entity when converting then verify list data converted is equal`() {
        val posts = (0..3).map { PostEntity(0L, "title", "body", 0L) }.toList()
        val expected = (0..3).map { Post(0L, "title", "body", 0L) }
        val res = posts.toPost()

        (posts.indices).forEach {
            Assert.assertEquals(res[it], expected[it])
        }
    }
}