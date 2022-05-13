package com.cwramirezg.noticia.data.source.remote

import com.cwramirezg.noticia.data.pojo.Response
import io.reactivex.Observable

interface DataSourceRemoteContract {
    fun getNoticiaApi(): Observable<Response>
}