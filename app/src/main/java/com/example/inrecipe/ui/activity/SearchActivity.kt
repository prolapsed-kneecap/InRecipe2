package com.example.inrecipe.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import com.example.inrecipe.R
import androidx.recyclerview.widget.LinearLayoutManager

import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inrecipe.adapter.SearchAdapter
import com.example.inrecipe.data.Data
import com.example.inrecipe.data.Dish


class SearchActivity : AppCompatActivity() {

    private lateinit var dishList: List<Dish>
    private lateinit var adapter: SearchAdapter

    private fun filter(text: String) {
        val filteredlist: MutableList<Dish> = mutableListOf()

        for (item in dishList) {
            if (item.name.lowercase().contains(text.lowercase())) {
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            adapter.filterList(filteredlist)
        } else {
            adapter.filterList(filteredlist)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        dishList = Data.dishes
        supportActionBar?.hide()

        adapter = SearchAdapter(Data.dishes, this)

        val searchView = findViewById<SearchView>(R.id.searchView)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText!!)
                return false
            }
        })

        val rv = findViewById<RecyclerView>(R.id.searchRv)

        rv.adapter = adapter
        rv.layoutManager = GridLayoutManager(this, 2)

    }
    fun inspectRecipe(position: Int) {
        val intent = Intent(this, RecipeInspectActivity::class.java)
        intent.putExtra("position", position)
        startActivity(intent)
    }
}