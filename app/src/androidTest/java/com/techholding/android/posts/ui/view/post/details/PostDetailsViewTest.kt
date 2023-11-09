package com.techholding.android.posts.ui.view.post.details

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.techholding.android.posts.model.Comment
import com.techholding.android.posts.model.Post
import com.techholding.android.posts.ui.theme.TechHoldingPostsTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PostDetailsViewTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    private fun setUpWithArgs(state: PostDetailUiState) {
        composeTestRule.setContent {
            TechHoldingPostsTheme {
                PostDetailsView(state = state)
            }
        }
    }

    @Test
    fun givenPostDetailsView_whenComposite_thenVerifyRootRodeExists() {
        setUpWithArgs(PostDetailUiState())
        composeTestRule.onRoot().assertExists()
    }

    @Test
    fun givenPostWithTitleAndBody_whenComposite_thenShouldShowHeader() {
        setUpWithArgs(
            PostDetailUiState(
                post = Post(1L, "title", "body", 1L)
            )
        )
        composeTestRule.onNodeWithTag("details_title").assertExists()
        composeTestRule.onNodeWithTag("details_title").assertTextEquals("TITLE")
        composeTestRule.onNodeWithTag("details_body").assertExists()
        composeTestRule.onNodeWithTag("details_body").assertTextEquals("body")
    }

    @Test
    fun givenPostWithComments_whenComposite_thenShouldShowManyComments() {
        val comments = (0..3).map { Comment(it.toLong(), "name$it", "email$it", "body$it", 1L) }
        setUpWithArgs(
            PostDetailUiState(
                post = Post(1L, "title", "body", 1L, comments)
            )
        )
        comments.onEach {
            composeTestRule.onNodeWithTag("comment_${it.id}").assertExists()
            composeTestRule.onNodeWithText("name${it.id}").assertIsDisplayed()
            composeTestRule.onNodeWithText("email${it.id}").assertIsDisplayed()
            composeTestRule.onNodeWithText("body${it.id}").assertIsDisplayed()
        }

    }

}