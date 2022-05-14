package com.cwramirezg.noticia.data.source

import com.cwramirezg.noticia.data.model.Noticia
import com.cwramirezg.noticia.data.pojo.Response
import com.cwramirezg.noticia.data.source.local.DataSourceLocalContract
import com.cwramirezg.noticia.data.source.remote.DataSourceRemoteContract
import com.cwramirezg.noticia.util.ExtensionStatic
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

class DataSourceRepository(
    private val local: DataSourceLocalContract,
    private val remote: DataSourceRemoteContract,
) : DataSourceRepositoryContract {
    override fun isOnline(): Boolean {
        return ExtensionStatic.isOnline()
    }

    override fun getNoticiaSave(noticia: List<Noticia>): Single<List<Long>> {
        return local.getNoticiaSave(noticia)
    }

    override fun getNoticia(): Flowable<List<Noticia>> {
        return local.getNoticia()
    }

    override fun getNoticiaApi(): Observable<Response> {
        return remote.getNoticiaApi()
    }

    override fun setLeido(noticia: Noticia): Completable {
        return local.setLeido(noticia)
    }
}