package com.example.moviedb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.R
import com.example.moviedb.services.MovieModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.grid_layout.view.*


class  RecyclerAdapter(var movieList : ArrayList<MovieModel>): RecyclerView.Adapter<RecyclerAdapter.MovieHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grid_layout, parent,false)
    return MovieHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }


    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(movieList[position])
    }

    class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView
    ){
        private val image: ImageView = itemView.posterImage
        private val title = itemView.movieTitle

        fun bind(movieModel: MovieModel){
            title.text = movieModel.Title
            Picasso.get().load(movieModel.Poster).into(image)


        }
    }
}