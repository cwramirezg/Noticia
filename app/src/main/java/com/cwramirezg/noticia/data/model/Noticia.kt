package com.cwramirezg.noticia.data.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(
    indices = [Index(value = ["storyId"], unique = true)]
)
@Parcelize
data class Noticia(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val storyId: Long,
    val storyTitle: String,
    val storyUrl: String,
    val commentText: String,
    val author: String,
    val leido: Boolean
) : Parcelable {
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
