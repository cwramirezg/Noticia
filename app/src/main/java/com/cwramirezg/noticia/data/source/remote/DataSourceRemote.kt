package com.cwramirezg.noticia.data.source.remote

import com.cwramirezg.noticia.data.pojo.Response
import io.reactivex.Observable

class DataSourceRemote(
    private val webServices: WebServices
) : DataSourceRemoteContract {

    override fun getNoticiaApi(): Observable<Response> {
        return webServices.listNoticia()
    }
}