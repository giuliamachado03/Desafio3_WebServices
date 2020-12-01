package com.example.desafio3.comic.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio3.R
import com.example.desafio3.comic.model.ComicModel

class ComicListAdapter(
    private val dataset: List<ComicModel>,
    private val listener: (ComicModel) -> Unit
) :
    RecyclerView.Adapter<ComicListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_comics_list_item, parent, false)
        return ComicListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComicListViewHolder, position: Int) {
        val item = dataset[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { listener(item) }
    }

    override fun getItemCount() = dataset.size
}





