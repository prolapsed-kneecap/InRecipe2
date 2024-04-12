package com.example.inrecipe.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.inrecipe.R
import com.example.inrecipe.adapter.DishPagerAdapter
import com.example.inrecipe.data.Data
import com.example.inrecipe.data.RecipesMaster
import com.example.inrecipe.ui.activity.MainActivity
import com.google.firebase.auth.FirebaseAuth

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        var position = arguments?.getInt("position")

        val favouriteFab =
            view.findViewById<com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton>(
                R.id.fab
            )

        val recipesMaster = RecipesMaster()
        val availableDishes = recipesMaster.getAvailableDishes(Data.dishes, Data.checked)
        Data.availableDishes = availableDishes
        val likeFab =
            view.findViewById<com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton>(
                R.id.fabLike
            )

        if (availableDishes.isEmpty()) {
            favouriteFab.visibility = View.GONE
            likeFab.visibility = View.GONE
            Toast.makeText((activity as MainActivity), "Выберите больше ингредиентов", Toast.LENGTH_SHORT).show()
        } else {
            likeFab.visibility = View.VISIBLE
            favouriteFab.visibility = View.VISIBLE
        }

        val viewPager = view.findViewById<ViewPager>(R.id.viewpager)
        val dishPagerAdapter = DishPagerAdapter(
            childFragmentManager,
            requireContext(),
            availableDishes
        )
        viewPager.adapter = dishPagerAdapter

        if (position != null) {
            viewPager.currentItem = position
        } else {
            viewPager.currentItem = 0
        }

        val mAuth = FirebaseAuth.getInstance()

        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                Data.database.collection("users").document(mAuth.currentUser!!.uid).get()
                    .addOnSuccessListener {
                        it["favorites"]?.let { favorites ->
                            favorites as List<Long>
                            if (favorites.contains(Data.availableDishes[viewPager.currentItem].index.toLong())) {
                                favouriteFab.icon =
                                    activity?.getDrawable(R.drawable.ic_baseline_bookmark_24)
                            } else {
                                favouriteFab.icon =
                                    activity?.getDrawable(R.drawable.baseline_bookmark_border_24)
                            }
                        }
                    }

                var likes = 0
                Data.database.collection("dishes")
                    .document(availableDishes[viewPager.currentItem].index.toString()).get()
                    .addOnSuccessListener {
                        it["rating"]?.let { rating ->
                            rating as List<String>
                            likeFab.text = (rating.size).toString()
                            if (Data.uid in rating)
                                likeFab.icon = activity?.getDrawable(R.drawable.heart_full)
                            else
                                likeFab.icon = activity?.getDrawable(R.drawable.heart_empty)
                        }
                    }
            }

            override fun onPageSelected(position: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}
        })




        favouriteFab.setOnClickListener {
            Data.database.collection("users").document(mAuth.currentUser!!.uid).get()
                .addOnSuccessListener {
                    it["favorites"]?.let { favorites ->
                        (favorites as MutableList<Long>)
                        favorites.distinct()
                        val dishIndex = availableDishes[viewPager.currentItem].index
                        if (favorites.contains(dishIndex.toLong())) {
                            favorites.remove(dishIndex.toLong())
                            Data.database.collection("users").document(mAuth.currentUser!!.uid)
                                .update("favorites", favorites)
                            favouriteFab.icon =
                                activity?.getDrawable(R.drawable.baseline_bookmark_border_24)
                        } else {
                            favorites.add(dishIndex.toLong())
                            Data.database.collection("users").document(mAuth.currentUser!!.uid)
                                .update("favorites", favorites)
                            favouriteFab.icon =
                                activity?.getDrawable(R.drawable.ic_baseline_bookmark_24)
                        }
                        Data.favorites = (favorites as List<Int>).toMutableSet()
                    }
                }
        }

        likeFab.setOnClickListener {
            val dishIndex = availableDishes[viewPager.currentItem].index
            Data.database.collection("dishes").document(dishIndex.toString()).get()
                .addOnSuccessListener {
                    it["rating"]?.let { rating ->
                        rating as MutableList<String>
                        if (Data.uid in rating) {
                            rating.remove(Data.uid)
                            likeFab.icon = activity?.getDrawable(R.drawable.heart_empty)
                            Data.database.collection("dishes").document(dishIndex.toString())
                                .update("rating", (rating))
                        } else {
                            rating.add(Data.uid)
                            Data.database.collection("dishes").document(dishIndex.toString())
                                .update("rating", (rating))
                            likeFab.icon = activity?.getDrawable(R.drawable.heart_full)
                        }

                        likeFab.text = ((rating as List<String>).size).toString()
                    }
                }
        }
        return view
    }

}