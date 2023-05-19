package com.example.lucflix

import android.graphics.drawable.LayerDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lucflix.model.Movie

class MovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        val txtTitle: TextView = findViewById(R.id.movie_txt_title)
        val txtDesc: TextView = findViewById(R.id.movie_txt_desc)
        val txtcast: TextView = findViewById(R.id.movie_txt_cast)
        val rv: RecyclerView = findViewById(R.id.movie_rv_similar)

        txtTitle.text = "Batman Begins"
        txtDesc.text = "Essa é a descrição do filme do Batman"
        txtcast.text = getString(R.string.cast, "Ator A, ator B, ator C")

        val movies = mutableListOf<Movie>()

        rv.layoutManager = GridLayoutManager(this, 3)
        rv.adapter = MovieAdapter(movies, R.layout.movie_item_similar)

        val toolbar: Toolbar = findViewById(R.id.movie_toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //Buscar o desenhavel(layer-list)
        val layerDrawable: LayerDrawable = ContextCompat.getDrawable(this, R.drawable.shadows) as LayerDrawable
        //Buscar o filme
        val movieCover = ContextCompat.getDrawable(this, R.drawable.movie_4)
        //atribuir a esse layer-list o novo filme
        layerDrawable.setDrawableByLayerId(R.id.cover_drawable, movieCover)
        //set imageview
        val coverImg: ImageView = findViewById(R.id.movie_img)
        coverImg.setImageDrawable(layerDrawable)

    }
}