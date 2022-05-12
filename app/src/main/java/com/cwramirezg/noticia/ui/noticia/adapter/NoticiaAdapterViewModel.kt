package com.cwramirezg.noticia.ui.noticia.adapter

import com.cwramirezg.noticia.data.model.Noticia

class NoticiaAdapterViewModel(
    private val item: Noticia,
) {

    val nombre
        get() = item.storyTitle

    val autor
        get() = "Autor: ${item.author}"

}