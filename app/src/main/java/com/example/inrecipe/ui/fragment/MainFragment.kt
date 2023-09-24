package com.example.inrecipe.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        val view = inflater.inflate(R.layout.main_fragment, container, false)

        var position = arguments?.getInt("position")

//        (activity as MainActivity).supportActionBar?.title = "Доступные рецепты"

        val favouriteFab =
            view.findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(
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
        } else {
            likeFab.visibility = View.VISIBLE
            favouriteFab.visibility = View.VISIBLE
        }
//        val adapter = MyAdapter(supportFragmentManager)

        val viewPager = view.findViewById<ViewPager>(R.id.viewpager)
//        val striped = view.findViewById<PagerTitleStrip>(R.id.pager_title_strip)
        val dishPagerAdapter = DishPagerAdapter(
            childFragmentManager,
            requireContext(),
            availableDishes
        )
        viewPager.adapter = dishPagerAdapter
//        viewPager.adapter = adapter
        if (position != null) {
            viewPager.currentItem = position
        } else {
            viewPager.currentItem = 0
        }
        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                var likes = 0
                Data.database.collection("dishes").document(availableDishes[viewPager.currentItem].index.toString()).get()
                    .addOnSuccessListener {
                        it["rating"]?.let { rating ->
                            likeFab.text = (rating.toString().toInt()).toString()
                        }
                    }
            }
            override fun onPageSelected(position: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}
        })




        favouriteFab.setOnClickListener {
            val mAuth = FirebaseAuth.getInstance()
            Data.database.collection("users").document(mAuth.currentUser!!.uid).get()
                .addOnSuccessListener {
                    it["favorites"]?.let {
                        Data.favorites = (it as List<Int>).toMutableSet()
                    }
                }
            val dishIndex = availableDishes[viewPager.currentItem].index
            if (Data.favorites.contains(dishIndex)) {
                Data.favorites.remove(dishIndex)
                Data.database.collection("users").document(mAuth.currentUser!!.uid)
                    .update("favorites", (Data.favorites).toList())

                Log.d("AAA", dishIndex.toString())
                Log.d("AAA", Data.favorites.toString())
//                favouriteFab.setBackgroundColor(resources.getColor(R.color.material_dynamic_neutral_variant50))
            } else {
                Data.favorites.add(dishIndex)
                Data.database.collection("users").document(mAuth.currentUser!!.uid)
                    .update("favorites", (Data.favorites).toList())
                Log.d("AAA", dishIndex.toString())
                Log.d("AAA", Data.favorites.toString())
//                favouriteFab.setBackgroundColor(resources.getColor(R.color.orange))
            }
        }

        likeFab.setOnClickListener {

//            Log.d("ASDFGH", availableDishes.toString())
            val dishIndex = availableDishes[viewPager.currentItem].index
//            Log.d("ASDFGH", dishIndex.toString())
//            lifecycleScope.launch(Dispatchers.IO) {
            val resu = Data.database.collection("dishes").document(dishIndex.toString()).get()
                .addOnSuccessListener {
                    it["rating"]?.let { rating ->

                        Data.database.collection("dishes").document(dishIndex.toString())
                            .update("rating", (rating.toString().toInt()) + 1)
                        likeFab.text = (rating.toString().toInt() + 1).toString()
                    }
                }
//            if (resu.isSuccessful){
//
//            }
//                    .get().result["rating"] as Int?

//                Log.d("ASDFGH", resu.toString())
//            }
//            rating?.let {
//
//                Data.database.collection("dishes").document(dishIndex.toString())
//                    .update("rating", it + 1)
//            }
//            likeFab.text = (rating?.plus(1)).toString()
        }



        return view
    }


}