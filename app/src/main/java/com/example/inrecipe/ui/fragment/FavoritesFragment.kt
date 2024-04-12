package com.example.inrecipe.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inrecipe.R
import com.example.inrecipe.adapter.FavoriteAdapter
import com.example.inrecipe.data.Data
import com.example.inrecipe.data.Dish
import com.example.inrecipe.ui.activity.MainActivity

class FavoritesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.favorites_fragment, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.favoritesRecyclerView)
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)

        val favs = mutableListOf<Dish>()

        val dishes = Data.dishes

//        val favoriteDishes = Data.dishes.filter { it.index in Data.favorites }
        val favoriteDishes = mutableListOf<Dish>()
        for (i in Data.favorites) {
            favoriteDishes.add(Data.dishes[i - 1])
        }

        recyclerView.adapter = FavoriteAdapter(favoriteDishes, (activity as MainActivity))
        Log.d("ABOBA", Data.favorites.size.toString())
        recyclerView.layoutManager = gridLayoutManager

        return view
    }
}