package com.example.inrecipe.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inrecipe.R
import com.example.inrecipe.adapter.FavoriteAdapter
import com.example.inrecipe.data.Data
import com.example.inrecipe.data.Dish

class FavoritesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.favorites_fragment, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.favoritesRecyclerView)
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)

        val favs = mutableListOf<Dish>()

        Data.favorites.forEach { n ->
            Data.dishes.forEach { dish ->
                if (n == dish.index) {
                    favs.add(dish)
                }
            }
        }

        recyclerView.adapter = FavoriteAdapter(favs, findNavController())
        recyclerView.layoutManager = gridLayoutManager

        return view
    }
}