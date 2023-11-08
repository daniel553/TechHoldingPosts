package com.techholding.android.posts.ui.view.post

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techholding.android.posts.model.Post

@Preview(showBackground = true)
@Composable
fun PostListViewPreview() {
    val posts = (0..3).map {
        Post(it.toLong(), "title ".repeat(20), "body ".repeat(100), 0)
    }
    PostListView(posts = posts, onPostSelected = {})
}

@Composable
fun PostListView(
    posts: List<Post>,
    onPostSelected: (id: Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(posts) { post ->
            PostItemView(post, onPressed = { postId ->
                onPostSelected(postId)
            })
        }
    }
}

@Composable
fun PostItemView(post: Post, onPressed: (id: Long) -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(84.dp)
                .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp)
                .clickable { onPressed(post.id) }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                Column(
                    Modifier.weight(0.8f)
                ) {
                    Text(
                        text = post.title.uppercase(),
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2,
                        softWrap = true,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    //Uncomment for body preview
                    /*
                    Text(
                        text = post.body,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 2,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                     */
                }
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .weight(0.1f)
                        .padding(start = 8.dp, end = 8.dp)
                )
            }
        }
    }
}

/**
 * There is a library to do this, but this is
 * an example of how to use an animation with a
 * linear gradient.
 */
@Composable
fun PostListShimmerView(
    modifier: Modifier = Modifier
) {
    val transition = rememberInfiniteTransition(label = "transition")
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        label = "transition",
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1200, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        ),
    )
    val brush = Brush.linearGradient(
        colors = listOf(
            Color.LightGray.copy(0.9f),
            Color.LightGray.copy(0.2f),
            Color.LightGray.copy(0.9f)
        ),
        start = Offset(10f, 10f),
        end = Offset(translateAnim, translateAnim)
    )

    LazyColumn(modifier = modifier) {
        items(10) {
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(84.dp)
                    .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp)

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(
                        Modifier.fillMaxWidth()
                    ) {
                        Spacer(
                            modifier = Modifier
                                .height(84.dp)
                                .fillMaxWidth()
                                .background(brush = brush)
                        )
                    }
                }
            }

        }
    }
}