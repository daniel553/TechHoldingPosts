package com.techholding.android.posts.ui.nav

/**
 * Screen router class
 */
sealed class Router(val path: String) {
    data object PostListScreen : Router("PostListScreen")
    data object PostDetailsScreen : Router("PostDetailsScreen".plus("/{$ID}"))

    fun buildRoute(arg: String): String {
        return path.replace(Regex("\\{(.*)?\\}"), "").plus(arg)
    }

    companion object {
        const val ID = "id"
    }
}