package com.example.application

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MoviesAdapter(val filmsList: ArrayList<Movies>) :
    RecyclerView.Adapter<MoviesAdapter.FilmsViewHolder>() {

    inner class FilmsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        val graphicUrlImageView: ImageView = itemView.findViewById(R.id.graphicUrlImageView)
        val backdropUrlImageView: ImageView = itemView.findViewById(R.id.backdropUrlImageView)
        val runTimeTextView: TextView = itemView.findViewById(R.id.runTimeTextView)



    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_cell, parent, false)
        return FilmsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        val currentItem = filmsList[position]
        holder.titleTextView.text = currentItem.title
        holder.descriptionTextView.text = currentItem.description
        holder.runTimeTextView.text= currentItem.runTime


        Glide.with(holder.graphicUrlImageView.context).load(currentItem.graphicUrl).into(holder.graphicUrlImageView)
        Glide.with(holder.backdropUrlImageView.context).load(currentItem.backdropUrl).into(holder.backdropUrlImageView)


        holder.graphicUrlImageView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MoviesDetailsActivity::class.java).apply {
                putExtra("film_description", currentItem.description)
                putExtra("film_img", currentItem.backdropUrl)
                putExtra("film_title", currentItem.title)
                putExtra("film_time", currentItem.runTime)
            }
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return filmsList.size
    }
}