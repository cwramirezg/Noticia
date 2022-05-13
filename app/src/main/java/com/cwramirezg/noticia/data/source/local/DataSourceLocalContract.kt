package com.cwramirezg.noticia.data.source.local

import com.cwramirezg.noticia.data.model.Noticia
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface DataSourceLocalContract {
    fun getNoticiaSave(noticia: List<Noticia>): Single<List<Long>>
    fun getNoticia(): Flowable<List<Noticia>>
    fun setLeido(noticia: Noticia): Completable
}