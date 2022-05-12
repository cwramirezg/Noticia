package com.cwramirezg.noticia.data.source

import com.cwramirezg.noticia.data.model.Noticia
import com.cwramirezg.noticia.data.pojo.Response
import com.cwramirezg.noticia.data.source.local.DataSourceLocalContract
import com.cwramirezg.noticia.data.source.remote.DataSourceRemoteContract
import io.reactivex.Observable
import io.reactivex.Single

class DataSourceRepository(
    private val local: DataSourceLocalContract,
    private val remote: DataSourceRemoteContract,
) : DataSourceRepositoryContract {

    override fun getNoticiaSave(): Single<Noticia> {
        TODO("Not yet implemented")
    }

    override fun getNoticia(): Observable<Response> {
        return remote.getNoticia()
    }
}