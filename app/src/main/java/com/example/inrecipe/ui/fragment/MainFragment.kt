package com.example.inrecipe.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.viewpager.widget.ViewPager
import com.example.inrecipe.data.Data
import com.example.inrecipe.adapter.DishPagerAdapter
import com.example.inrecipe.R
import com.example.inrecipe.data.Dish
import com.example.inrecipe.data.RecipesMaster
import com.example.inrecipe.ui.activity.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_fragment, container, false)

//        (activity as MainActivity).supportActionBar?.title = "Доступные рецепты"

        val recipesMaster = RecipesMaster()
        val availableDishes = recipesMaster.getAvailableDishes(Data.dishes, Data.checked)
        Data.availableDishes = availableDishes

//        val adapter = MyAdapter(supportFragmentManager)

        val viewPager = view.findViewById<ViewPager>(R.id.viewpager)
//        val striped = view.findViewById<PagerTitleStrip>(R.id.pager_title_strip)
        val dishPagerAdapter = DishPagerAdapter((activity as MainActivity).supportFragmentManager, requireContext(), availableDishes)
        viewPager.adapter = dishPagerAdapter
//        viewPager.adapter = adapter
        viewPager.currentItem = 0

        val favouriteFab = view.findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.fab)
        val likeFab = view.findViewById<com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton>(R.id.fabLike)

        favouriteFab.setOnClickListener{
            val mAuth = FirebaseAuth.getInstance()
            val dishIndex = availableDishes[viewPager.currentItem].index
            if (Data.favorites.contains(dishIndex)){
                Data.favorites.remove(dishIndex)
                Data.database.collection("users").document(mAuth.currentUser!!.uid).update("favorites", Data.favorites.toList())

//                favouriteFab.setBackgroundColor(resources.getColor(R.color.material_dynamic_neutral_variant50))
            }
            else{
                Data.favorites.add(dishIndex)
                Data.database.collection("users").document(mAuth.currentUser!!.uid).update("favorites", Data.favorites.toList())

//                favouriteFab.setBackgroundColor(resources.getColor(R.color.orange))
            }
        }

        likeFab.setOnClickListener{

//            Log.d("ASDFGH", availableDishes.toString())
            val dishIndex = availableDishes[viewPager.currentItem].index
//            Log.d("ASDFGH", dishIndex.toString())
//            lifecycleScope.launch(Dispatchers.IO) {
                val resu = Data.database.collection("dishes").document(dishIndex.toString()).get().addOnSuccessListener {
                    it["rating"]?.let { rating ->

                            Data.database.collection("dishes").document(dishIndex.toString())
                                .update("rating", (rating.toString().toInt()) + 1)
                        Toast.makeText(requireContext(), rating.toString(), Toast.LENGTH_SHORT).show()
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