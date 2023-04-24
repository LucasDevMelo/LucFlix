//o ciclo de vida de uma atividade

package com.example.lucflix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lucflix.model.Movie

class MainActivity : AppCompatActivity() {

    //m-v-c (model = [view/controler] activity)
    override fun onCreate(savedInstanceState: Bundle?) {
        //define variáveis e coisas da interface gáfica (1)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("Teste", "onCreate")

        val movies = mutableListOf<Movie>()
        for(i in 0 until 5){
            val movie = Movie(R.drawable.movie)
            movies.add(movie)
        }

        val adapter = MainAdapter(movies)
        val rv: RecyclerView = findViewById(R.id.rv_main)
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rv.adapter = adapter
    }

}