package com.shixforever.gradlestudy.okhttp

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NetInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        Log.e("NetInterceptor","进入拦截器，请求头加一个参数")
        val newRequest: Request = request.newBuilder().addHeader("test","test1").build();
        return chain.proceed(newRequest);
    }
}