package com.hasanqadir.developer.json_feed.utils

object Constants {
    const val API_BASE_URL: String = "https://www.googleapis.com/books/"
    const val VOLUME_AUTHOR = "inauthor:"
}

object NetworkConstants {
    private const val V1 = "v1/"
    private const val VOLUMES = "volumes"
    const val GET_VOLUMES = V1.plus(VOLUMES)
}