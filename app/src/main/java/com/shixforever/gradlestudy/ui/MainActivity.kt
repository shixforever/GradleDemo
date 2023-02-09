package com.shixforever.gradlestudy.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.shixforever.gradlestudy.R
import com.shixforever.gradlestudy.aop.AopUtil
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var textView : TextView
    private lateinit var button : Button
    val handler:Handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button_okhttp);
        textView = findViewById(R.id.tv_response);
        button.setOnClickListener{
            doOkHttpRequest()
        }
    }

    private fun doOkHttpRequest(){
        val okHttpClient = OkHttpClient().newBuilder().build()
        val request: Request = Request.Builder()
            .url("https://httpbin.org/get")
            .build();
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("doOkHttpRequest","request fail")
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                Log.e("doOkHttpRequest","request success")
                if(response.body() == null){
                    return
                }
                val string = response.body()!!.string();
                handler.post{
                    textView.text= string
                }
            }
        })
    }

}