package com.techholding.android.posts.ui.view.post.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techholding.android.posts.model.Comment
import com.techholding.android.posts.model.Post

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PostDetailsView(state: PostDetailUiState, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        state.post?.let { post ->
            stickyHeader {
                PostDetailsHeaderView(post.title, post.body)
            }
        }
        state.post?.comments?.let { comments ->
            items(comments) { comment ->
                CommentItemView(comment)
            }
        }

    }
}

@Composable
fun PostDetailsHeaderView(title: String, body: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Column(
                Modifier.weight(0.8f)
            ) {
                Text(
                    text = title.uppercase(),
                    style = MaterialTheme.typography.titleMedium,
                    softWrap = true,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .testTag("details_title")
                )
                Text(
                    text = body,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .testTag("details_body")
                )
            }
        }
    }
}

@Composable
fun CommentItemView(comment: Comment) {
    with(comment) {
        Column(
            modifier = Modifier
                .padding(top = 4.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth()
                .testTag("comment_${comment.id}")
        ) {
            Row(
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = "User",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp)
                        .padding(start = 4.dp, end = 4.dp)
                )
                Column {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier
                            .padding(top = 4.dp)
                    )
                    Text(
                        text = email,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    )
                }

            }
            Column(
                Modifier.padding(start = 32.dp)
            ) {

                Text(
                    text = body,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostDetailsViewPreview() {
    val state = PostDetailUiState(post = Post(
        id = 1L,
        title = "Title ".repeat(10),
        body = "body ".repeat(30),
        userId = 1L,
        comments = (1..5).map {
            Comment(
                id = it.toLong(),
                name = "name $it",
                email = "email@email.com",
                body = "body ".repeat(30),
                postId = 1L
            )
        }
    ))

    PostDetailsView(state = state)
}