@file:JvmName("AopUtil")
package com.shixforever.gradlestudy.aop

import android.util.Log
import com.shixforever.gradlestudy.okhttp.HttpEventListener
import com.shixforever.gradlestudy.okhttp.NetInterceptor
import okhttp3.OkHttpClient

object AopUtil {
    @JvmStatic
    fun hookOkHttp(instance: Any) {
        Log.e("AopUtil", "call hookOkHttp")
        if (instance is OkHttpClient.Builder) {
            try {
                // 添加网络监听
                instance.eventListenerFactory(HttpEventListener.FACTORY)
                // 添加拦截器
                instance.addNetworkInterceptor(NetInterceptor())
            } catch (e: Exception) {
                Log.e("AopUtil", "hook error")
            }
        }
    }
}