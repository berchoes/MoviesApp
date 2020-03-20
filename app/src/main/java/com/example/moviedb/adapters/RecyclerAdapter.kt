package com.example.moviedb.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.R
import com.example.moviedb.services.MovieModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.grid_layout.view.*


class RecyclerAdapter(var context: Context, var movieList : ArrayList<MovieModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grid_layout, parent,false)
    return ItemHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(movieModel: MovieModel){
           // itemView.posterImage.setImageDrawable(Picasso.get().load)
        }
    }
}