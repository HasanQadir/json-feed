package com.hasanqadir.developer.json_feed.restclient.core

import com.hasanqadir.developer.json_feed.App
import com.hasanqadir.developer.json_feed.R
import com.hasanqadir.developer.json_feed.utils.isConnectedToInternet
import okhttp3.*
import java.io.IOException

class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val app = App.instance
        if (!app.isConnectedToInternet()) {
            throw IOException(app.getString(R.string.internet_not_available))
        }

        var request = chain.request()

        request =
            request.newBuilder()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .build()

        val response = chain.proceed(request)
        return response
    }

}