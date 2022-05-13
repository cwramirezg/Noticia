package com.cwramirezg.noticia.data.source.local

import com.cwramirezg.noticia.data.model.Noticia
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

class DataSourceLocal(
    private val database: Database
) : DataSourceLocalContract {

    override fun getNoticiaSave(noticia: List<Noticia>): Single<List<Long>> {
        return database.noticiaDao().insert(noticia)
    }

    override fun getNoticia(): Flowable<List<Noticia>> {
        return database.noticiaDao().getByLeido()
    }

    override fun setLeido(noticia: Noticia): Completable {
        return database.noticiaDao().update(noticia)
    }
}