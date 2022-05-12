package com.cwramirezg.noticia.data.source

import com.cwramirezg.noticia.data.source.local.DataSourceLocal
import com.cwramirezg.noticia.data.source.local.DataSourceLocalContract
import com.cwramirezg.noticia.data.source.remote.DataSourceRemote
import com.cwramirezg.noticia.data.source.remote.DataSourceRemoteContract
import org.koin.dsl.module

val dataSourceRepositoryModule = module {
    factory<DataSourceLocalContract> { DataSourceLocal(get()) }
    factory<DataSourceRemoteContract> { DataSourceRemote(get()) }
    single<DataSourceRepositoryContract> {
        DataSourceRepository(get(), get())
    }
}
