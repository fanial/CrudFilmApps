package com.fal.crudfilmapps.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fal.crudfilmapps.R
import com.fal.crudfilmapps.databinding.ItemListBinding
import com.fal.crudfilmapps.fragment.DeleteFragmentDialog
import com.fal.crudfilmapps.fragment.FragmentDetail
import com.fal.crudfilmapps.fragment.FragmentUpdate
import com.fal.crudfilmapps.model.DataFilm
import com.fal.crudfilmapps.model.PutResponseFilm
import com.fal.crudfilmapps.model.ResponseDataFilmItem

class AdapterFilm(var itemFilm : List<ResponseDataFilmItem> ): RecyclerView.Adapter<AdapterFilm.ViewHolder>() {
    var onEdit :((PutResponseFilm)->Unit)? = null
    class ViewHolder(val binding : ItemListBinding): RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterFilm.ViewHolder, @Suppress("RecyclerView") position: Int) {
        Glide.with(holder.itemView).load(itemFilm[position].image).into(holder.binding.image)
        holder.binding.name.text = itemFilm[position].name
        holder.binding.director.text = itemFilm[position].director

//        // Update Data Done
//        holder.binding.btnDetail.setOnClickListener{
//            val bundle = Bundle()
//            bundle.putInt("update",itemFilm[position].id.toInt())
//            Navigation.findNavController(it).navigate(R.id.action_fragmentHome_to_fragmentUpdate,bundle)
//        }

        holder.binding.btnDetail.setOnClickListener{
            val bundle = Bundle()
            bundle.putInt("delete",itemFilm[position].id.toInt())
            Navigation.findNavController(it).navigate(R.id.action_fragmentHome_to_deleteFragmentDialog,bundle)

            val view = View.inflate(it.context,R.layout.fragment_delete_dialog,null)
            val builder = AlertDialog.Builder(it.context)
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)


        }
    }

    override fun getItemCount(): Int {
        return itemFilm.size
    }

    fun setData(data : ArrayList<ResponseDataFilmItem>){
        this.itemFilm = data
    }
}