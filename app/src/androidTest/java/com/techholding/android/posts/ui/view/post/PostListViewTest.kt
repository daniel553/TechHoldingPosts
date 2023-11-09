package com.techholding.android.posts.ui.view.post

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.techholding.android.posts.model.Post
import com.techholding.android.posts.ui.theme.TechHoldingPostsTheme
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PostListViewTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    var clicked: Long = 0L
    val event: (id: Long) -> Unit = { clicked = it }

    val posts = (0..3).map { Post(it.toLong(), "title_$it", "body", 0L) }

    @Before
    fun setUp() {
        clicked = 0L
        composeTestRule.setContent {
            TechHoldingPostsTheme {
                PostListView(posts = posts, onPostSelected = { event(it) })
            }
        }
    }

    @Test
    fun givenPostListView_whenComposite_thenVerifyRootRodeExists() {
        composeTestRule.onRoot().assertExists()
    }

    @Test
    fun givenPostList_whenComposite_thenVerifyExist() {
        val res = mutableListOf<SemanticsNodeInteraction>()
        posts.onEach {
            res.add(
                composeTestRule.onNodeWithTag("item_${it.id}", useUnmergedTree = true)
                    .assertExists()
            )
        }
        Assert.assertEquals(posts.size, res.size)
    }


    @Test
    fun givenPost_whenSelected_thenVerifyOnSelectedIdIsCorrect() {
        composeTestRule.onNodeWithTag("item_1").performClick()
        Assert.assertEquals(1, clicked)
        Assert.assertNotEquals(0, clicked)
        composeTestRule.onNodeWithTag("item_2").performClick()
        Assert.assertEquals(2, clicked)

    }
}