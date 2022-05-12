package com.cwramirezg.noticia.data.source.remote

import android.app.Application
import com.cwramirezg.noticia.BuildConfig
import com.cwramirezg.noticia.data.source.local.DataSourceLocalContract
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit

val webServiceModule = module {
    factory { provideFile(get()) }
    factory { provideCache(get()) }
    factory { provideGson() }
    factory { provideHeaderInterceptor(get()) }
    factory { provideHttpLoggingInterceptor() }
    factory { provideOkHttpClient(get(), get(), get()) }
    factory { provideRetrofit(get(), get()) }
    factory { provideWebService(get()) }
}

fun provideFile(application: Application): File {
    return File(application.cacheDir, "okhttp_cache")
}

fun provideCache(file: File): Cache {
    return Cache(file, 10 * 1000 * 1000)
}

fun provideGson(): Gson {
    return GsonBuilder().create()
}

fun provideHeaderInterceptor(
    dataSourceLocal: DataSourceLocalContract
): HeaderInterceptor {
    return HeaderInterceptor(dataSourceLocal)
}

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    var httpLoggingInterceptor =
        HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.d(message)
            }
        }
        )
    httpLoggingInterceptor.apply {
        this.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }
    return httpLoggingInterceptor
}

fun provideOkHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor,
    headerInterceptor: HeaderInterceptor,
    cache: Cache
): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(headerInterceptor)
        .readTimeout(90, TimeUnit.SECONDS)
        .writeTimeout(90, TimeUnit.SECONDS)
        .connectTimeout(90, TimeUnit.SECONDS)
        .cache(cache)
        .build()
}

fun provideRetrofit(
    gson: Gson,
    okHttpClient: OkHttpClient
): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .baseUrl(BuildConfig.BASE_URL_IP)
        .build()
}

fun provideWebService(retrofit: Retrofit): WebServices {
    return retrofit.create(WebServices::class.java)
}