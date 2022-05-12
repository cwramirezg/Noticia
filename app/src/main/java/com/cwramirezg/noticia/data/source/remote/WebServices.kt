package com.cwramirezg.noticia.data.source.remote

import com.cwramirezg.noticia.data.pojo.Response
import io.reactivex.Observable
import retrofit2.http.GET

interface WebServices {

    @GET(Urls.LIST_NOTICIA)
    fun listNoticia(): Observable<Response>

}