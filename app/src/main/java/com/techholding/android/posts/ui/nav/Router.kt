package com.techholding.android.posts.ui.nav

/**
 * Screen router class
 */
sealed class Router(val path: String) {
    data object PostListScreen : Router("PostListScreen".plus("/{$ID}"))
    data object PostDetailsScreen : Router("PostDetailsScreen".plus("/{$ID}"))
    data object PostCreateScreen : Router("PostCreateScreen")

    fun buildRoute(arg: String): String {
        return path.replace(Regex("\\{(.*)?\\}"), "").plus(arg)
    }

    companion object {
        const val ID = "id"
    }
}