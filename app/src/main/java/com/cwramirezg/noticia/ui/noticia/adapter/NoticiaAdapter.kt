package com.cwramirezg.noticia.ui.noticia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cwramirezg.noticia.data.model.Noticia
import com.cwramirezg.noticia.databinding.ItemNoticiaBinding

class NoticiaAdapter(
    val onclick: (Int) -> Unit
) : ListAdapter<Noticia, NoticiaAdapter.ViewHolder>(Noticia.DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNoticiaBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onclick
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemNoticiaBinding,
        val onclick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                onclick(bindingAdapterPosition)
            }
        }

        fun bind(obj: Noticia) {
            with(binding) {
                viewModel = NoticiaAdapterViewModel(obj)
                executePendingBindings()
            }
        }
    }
}