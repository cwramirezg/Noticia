package com.cwramirezg.noticia.data.source.remote

import android.annotation.SuppressLint
import com.cwramirezg.noticia.data.source.local.DataSourceLocalContract
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(
    private val dataSourceLocal: DataSourceLocalContract
) : Interceptor {

    @SuppressLint("CheckResult")
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        return chain.proceed(original)
    }
}