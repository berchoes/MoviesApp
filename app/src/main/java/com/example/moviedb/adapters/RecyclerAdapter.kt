package com.example.moviedb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.R
import com.example.moviedb.services.MovieModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.grid_layout.view.*


class RecyclerAdapter(var movieList : ArrayList<MovieModel>) : RecyclerView.Adapter<RecyclerAdapter.ItemHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grid_layout, parent,false)
    return ItemHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(movieList[position])
    }

    class ItemHolder(view: View) : RecyclerView.ViewHolder(view){

        fun bind(movieModel: MovieModel){

            itemView.movieTitle.text = movieModel.Title
            Picasso.get().load(movieModel.Poster).into(itemView.posterImage)
        }
    }
}