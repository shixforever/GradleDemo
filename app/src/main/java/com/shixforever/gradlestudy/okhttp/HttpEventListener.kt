package com.shixforever.gradlestudy.okhttp

import android.util.Log
import okhttp3.Call
import okhttp3.EventListener
import okhttp3.EventListener.Factory
import java.io.IOException

class HttpEventListener : EventListener() {
    companion object {
        val FACTORY = Factory {
            HttpEventListener()
        }
    }

    override fun callStart(call: Call) {
        Log.e("HttpEventListener","请求开始")
        super.callStart(call)
    }
}