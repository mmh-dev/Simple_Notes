package com.smh.simple_notes.retrofit

import android.app.Application
import com.parse.Parse
import com.smh.simple_notes.R

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId(Utils.API_KEY)
                .clientKey(Utils.clientKey)
                .server(Utils.BASE_URL)
                .build());
    }
}