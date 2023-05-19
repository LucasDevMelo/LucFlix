package com.example.lucflix

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.lucflix.model.Movie
import com.example.lucflix.util.DownloadImageTask
import com.squareup.picasso.Picasso

// Aqui é lista HORIZONTAL
class MovieAdapter(private val movies: List<Movie>,
    @LayoutRes private val layoutID: Int,
    private val onItemClickListener: ( (Int)-> Unit )? = null
    ) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutID, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            val imageCover: ImageView = itemView.findViewById(R.id.img_cover)
            imageCover.setOnClickListener{
                onItemClickListener?.invoke(movie.id)
            }


//            Adicionar as imagens usando framework
//            Picasso.get().load(movie.coverUrl).into(imageCover)
            //Outro método(mais complicado e menos utilizado)
            DownloadImageTask(object : DownloadImageTask.Callback{
                override fun onResult(bitmap: Bitmap) {
                    imageCover.setImageBitmap(bitmap)
                }
            }).execute(movie.coverUrl)

        }
    }

}