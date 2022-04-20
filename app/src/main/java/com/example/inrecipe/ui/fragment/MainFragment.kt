package com.example.inrecipe.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.inrecipe.data.Data
import com.example.inrecipe.adapter.DishPagerAdapter
import com.example.inrecipe.R
import com.example.inrecipe.data.RecipesMaster
import com.example.inrecipe.ui.activity.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.ktx.Firebase

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_fragment, container, false)

//        (activity as MainActivity).supportActionBar?.title = "Доступные рецепты"

        val favouriteFab =
            view.findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(
                R.id.fab
            )

        val recipesMaster = RecipesMaster()
        val availableDishes = recipesMaster.getAvailableDishes(Data.dishes, Data.checked)
        Data.availableDishes = availableDishes

        if (availableDishes.isEmpty()) {
            favouriteFab.visibility = View.GONE
        } else {
            favouriteFab.visibility = View.VISIBLE
        }

//        val adapter = MyAdapter(supportFragmentManager)

        val viewPager = view.findViewById<ViewPager>(R.id.viewpager)
//        val striped = view.findViewById<PagerTitleStrip>(R.id.pager_title_strip)
        val dishPagerAdapter = DishPagerAdapter(
            (activity as MainActivity).supportFragmentManager,
            requireContext(),
            availableDishes
        )
        viewPager.adapter = dishPagerAdapter
//        viewPager.adapter = adapter
        viewPager.currentItem = 0

        favouriteFab.setOnClickListener {
            val mAuth = FirebaseAuth.getInstance()
            val dishIndex = availableDishes[viewPager.currentItem].index
            if (Data.favorites.contains(dishIndex)) {
                Data.favorites.remove(dishIndex)
                Data.database.collection("users").document(mAuth.currentUser!!.uid)
                    .update("favorites", Data.favorites.toSet().toList()).addOnFailureListener {
                        Log.d("ABOBA_1", it.message.toString())
                    }

//                favouriteFab.setBackgroundColor(resources.getColor(R.color.material_dynamic_neutral_variant50))
            } else {
                Data.favorites.add(dishIndex)
                Data.database.collection("users").document(mAuth.currentUser!!.uid)
                    .update("favorites", Data.favorites.toSet().toList()).addOnFailureListener {
                        Log.d("ABOBA_2", it.message.toString())
                    }

//                favouriteFab.setBackgroundColor(resources.getColor(R.color.orange))
            }
        }

        return view
    }


}