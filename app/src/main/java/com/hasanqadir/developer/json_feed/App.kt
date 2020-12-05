package com.hasanqadir.developer.json_feed

import android.app.Application
import com.hasanqadir.developer.json_feed.restclient.core.ServerInjector

class App : Application() {

    companion object {
        lateinit var instance: App
        private val apiService = ServerInjector.getInstance().provideApiService()
    }
}