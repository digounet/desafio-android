package com.pablo.desafio.features.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.pablo.desafio.R
import com.pablo.desafio.model.data.Movie
import kotlinx.android.synthetic.main.row_movie.view.*


class MovieListAdapter(private val values: List<Movie>, private val onClickListener: (Movie)->Unit) :
        RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private val glideOptions = RequestOptions()
            .centerCrop()
            .priority(Priority.HIGH)
            .diskCacheStrategy(DiskCacheStrategy.ALL)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = values.size

    override fun onBindViewHolder(holder: MovieListAdapter.ViewHolder, position: Int) {
        val item = values[position]
        holder.txtTitle.text = item.name

        Glide.with(holder.itemView.context)
                .load(item.url)
                .apply(glideOptions)
                .into(holder.image)

        with(holder.itemView) {
            tag = item
            setOnClickListener{ onClickListener(item) }
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtTitle: TextView = view.txtTitle
        val image: ImageView = view.imageView
    }
}