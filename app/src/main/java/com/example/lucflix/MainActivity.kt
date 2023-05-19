//o ciclo de vida de uma atividade

package com.example.lucflix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lucflix.model.Category
import com.example.lucflix.model.Movie
import com.example.lucflix.util.CategoryTask

class MainActivity : AppCompatActivity(), CategoryTask.Callback {
    // m-v-c (model - [view/controller] activity)
    private lateinit var progress: ProgressBar
    private lateinit var adapter: CategoryAdapter
    private val categories = mutableListOf<Category>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progress = findViewById(R.id.progress_main)

        // list vertical
        // cat 1
        // listas h-> - f1 - f2 -f3 -f4 ...
        // cat 2
        // listas h-> - f1 - f2 -f3 -f4 ...

        // na vertical a lista (CategoryAdapter) de categorias
        // e dentro de cada item [TextView+RecyclerView horizontal]
        // (cada categoria) teremos
        // uma lista (MovieAdapter) de filmes (ImageView)
        adapter = CategoryAdapter(categories)
        val rv: RecyclerView = findViewById(R.id.rv_main)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

        CategoryTask(this).execute("https://api.tiagoaguiar.co/netflixapp/home?apiKey=4407a4eb-ed0c-4700-8a41-f381ddf90e4d")
    }

    override fun onPreExecute() {
        progress.visibility = View.VISIBLE
    }

    override fun onResult(categories: List<Category>) {
        // aqui será quando o CategoryTask chamará de volta
        // (callback) - listener
        this.categories.clear()
        this.categories.addAll(categories)
        adapter.notifyDataSetChanged() //forca o adapter a chamar denovo o onBindViewHolder

        progress.visibility = View.GONE
    }

    override fun onFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        progress.visibility = View.GONE
    }

}