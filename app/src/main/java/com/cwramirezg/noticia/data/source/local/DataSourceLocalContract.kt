package com.cwramirezg.noticia.data.source.local

import com.cwramirezg.noticia.data.model.Noticia
import io.reactivex.Single

interface DataSourceLocalContract {
    fun getNoticiaSave(): Single<Noticia>
}