package com.cwramirezg.noticia.data.source.local

import com.cwramirezg.noticia.data.model.Noticia
import io.reactivex.Single

class DataSourceLocal(
    private val database: Database
) : DataSourceLocalContract {

    override fun getNoticiaSave(): Single<Noticia> {
        TODO("Not yet implemented")
    }
}