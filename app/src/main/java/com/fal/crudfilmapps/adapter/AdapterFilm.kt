package com.fal.crudfilmapps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fal.crudfilmapps.databinding.ItemListBinding
import com.fal.crudfilmapps.model.ResponseDataFilmItem

class AdapterFilm(val itemFilm : List<ResponseDataFilmItem> ): RecyclerView.Adapter<AdapterFilm.ViewHolder>() {
    class ViewHolder(val binding : ItemListBinding): RecyclerView.ViewHolder(TODO()) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView).load(itemFilm[position].image).into(holder.binding.image)
        holder.binding.name.text = itemFilm[position].name
        holder.binding.director.text = itemFilm[position].director
        holder.binding.btnDetail.setOnClickListener {
            TODO("implement navcomp to detail fragment")
        }

    }

    override fun getItemCount(): Int {
        return itemFilm.size
    }
}