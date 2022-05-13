package com.cwramirezg.noticia.injection

import android.os.Bundle
import com.cwramirezg.noticia.data.source.dataSourceRepositoryModule
import com.cwramirezg.noticia.data.source.local.dataBaseModule
import com.cwramirezg.noticia.data.source.remote.webServiceModule
import com.cwramirezg.noticia.ui.detalle.DetalleViewModel
import com.cwramirezg.noticia.ui.noticia.NoticiaViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Modules {
    fun get() = listOf(
        dataBaseModule,
        webServiceModule,
        dataSourceRepositoryModule,
        module {
            viewModel { NoticiaViewModel(get()) }
            viewModel { (bundle: Bundle?) ->
                DetalleViewModel(
                    bundle,
                    get()
                )
            }
        }
    )
}
