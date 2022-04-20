package com.example.inrecipe.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import com.example.inrecipe.R
import androidx.recyclerview.widget.LinearLayoutManager

import android.widget.Toast
import com.example.inrecipe.data.Data
import com.example.inrecipe.data.Dish


class SearchActivity : AppCompatActivity() {

    lateinit var dishList: MutableSet<Dish>

    private fun filter(text: String) {
        val filteredlist: MutableSet<Dish> = mutableSetOf()

        for (item in dishList) {
            if (item.name.lowercase().contains(text.lowercase())) {
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            adapter.filterList(filteredlist)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        dishList = Data.dishes

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

        val rv = findViewById<>()

    }
}