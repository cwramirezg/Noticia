package com.cwramirezg.noticia.data.model

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Noticia(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val storyId: Long,
    val storyTitle: String,
    val storyUrl: String,
    val commentText: String,
    val author: String,
    val leido: Boolean
) {
    @Ignore
    constructor() : this(
        0,
        0,
        "",
        "",
        "",
        "",
        false
    )

    class DiffCallback : DiffUtil.ItemCallback<Noticia>() {
        override fun areItemsTheSame(oldItem: Noticia, newItem: Noticia): Boolean {
            return oldItem.storyId == newItem.storyId
        }

        override fun areContentsTheSame(oldItem: Noticia, newItem: Noticia): Boolean {
            return oldItem == newItem
        }

    }
}
