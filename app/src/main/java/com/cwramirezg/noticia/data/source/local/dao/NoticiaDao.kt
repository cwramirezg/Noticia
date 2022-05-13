package com.cwramirezg.noticia.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.cwramirezg.noticia.data.model.Noticia
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface NoticiaDao : BaseDao<Noticia> {

    @Query("SELECT * FROM Noticia")
    fun getAll(): Single<List<Noticia>>

    @Query("DELETE FROM Noticia")
    fun deleteAll()

    @Query("SELECT * FROM Noticia WHERE leido = 0")
    fun getByLeido(): Flowable<List<Noticia>>
}
