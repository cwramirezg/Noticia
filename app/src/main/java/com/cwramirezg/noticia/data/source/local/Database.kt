package com.cwramirezg.noticia.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cwramirezg.noticia.data.model.Noticia
import com.cwramirezg.noticia.data.source.local.dao.NoticiaDao

@Database(
    entities = [Noticia::class],
    version = 1
)
@TypeConverters(
    Converters::class
)
abstract class Database : RoomDatabase() {
    abstract fun noticiaDao(): NoticiaDao
}