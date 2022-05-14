package com.cwramirezg.noticia.data.source

import com.cwramirezg.noticia.data.source.local.DataSourceLocalContract
import com.cwramirezg.noticia.data.source.remote.DataSourceRemoteContract

interface DataSourceRepositoryContract : DataSourceLocalContract, DataSourceRemoteContract {
    fun isOnline(): Boolean
}