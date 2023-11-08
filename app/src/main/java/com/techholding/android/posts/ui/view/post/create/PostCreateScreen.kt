package com.techholding.android.posts.ui.view.post.create

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.techholding.android.posts.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostCreateScreen(navController: NavController, viewModel: PostCreateViewModel) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(text = stringResource(id = R.string.post_create_title).uppercase())
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        with(state) {
            Box(modifier = Modifier.padding(padding)) {
                PostFormView(
                    title = post.title,
                    body = post.body,
                    loading = loading,
                    onChange = { t, b ->
                        viewModel.updatePost(t, b)
                    },
                    onCreate = {
                        viewModel.doPost()
                    }
                )
            }
        }
    }
}

@Composable
fun PostFormView(
    title: String,
    body: String,
    loading: Boolean,
    onChange: (title: String, body: String) -> Unit,
    onCreate: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                isError = title.isEmpty(),
                maxLines = 1,
                onValueChange = { onChange(it, body) },
                label = {
                    Text(text = stringResource(id = R.string.post_form_title))
                },
                modifier = Modifier.fillMaxWidth()
            )
            if (title.isEmpty()) {
                Text(
                    text = stringResource(id = R.string.post_form_title_sub),
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            OutlinedTextField(
                value = body,
                isError = body.isEmpty(),
                onValueChange = { onChange(title, it) },
                maxLines = 3,
                minLines = 2,
                label = {
                    Text(text = stringResource(id = R.string.post_form_body))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
            if (body.isEmpty()) {
                Text(
                    text = stringResource(id = R.string.post_form_body_sub),
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(32.dp)
                        .padding(top = 8.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    trackColor = MaterialTheme.colorScheme.secondary,
                )
            } else {
                ElevatedButton(
                    enabled = !loading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    onClick = { if (!loading) onCreate() }
                ) {
                    if (loading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .height(26.dp)
                                .width(26.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            trackColor = MaterialTheme.colorScheme.secondary,
                        )
                    } else {
                        Text(text = stringResource(id = R.string.post_form_add_button).uppercase())
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostFormViewPreview() {
    PostFormView(
        title = "Title",
        body = "Body ".repeat(20),
        loading = false,
        onChange = { _, _ -> },
        onCreate = {})
}