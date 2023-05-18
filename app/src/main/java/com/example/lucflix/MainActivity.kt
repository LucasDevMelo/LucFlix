//o ciclo de vida de uma atividade

package com.example.lucflix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lucflix.model.Category
import com.example.lucflix.model.Movie

class MainActivity : AppCompatActivity() {

    // m-v-c (model - [view/controller] activity)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("Teste", "onCreate")

        // list vertical
        // cat 1
        // listas h-> - f1 - f2 -f3 -f4 ...
        // cat 2
        // listas h-> - f1 - f2 -f3 -f4 ...

        val categories = mutableListOf<Category>()
        for (j in 0 until 5) {
            val movies = mutableListOf<Movie>()
            for (i in 0 until 15) {
                val movie = Movie(R.drawable.movie)
                movies.add(movie)
            }
            val category = Category("cat $j", movies)
            categories.add(category)
        }

        // na vertical a lista (CategoryAdapter) de categorias
        // e dentro de cada item [TextView+RecyclerView horizontal]
        // (cada categoria) teremos
        // uma lista (MovieAdapter) de filmes (ImageView)
        val adapter = CategoryAdapter(categories)
        val rv: RecyclerView = findViewById(R.id.rv_main)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
    }

}