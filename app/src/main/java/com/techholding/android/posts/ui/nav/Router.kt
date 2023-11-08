package com.techholding.android.posts.ui.nav

/**
 * Screen router class
 */
sealed class Router(val path: String) {
    data object PostListScreen : Router("PostListScreen")
}