package com.example.inrecipe

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerTitleStrip
import androidx.viewpager.widget.ViewPager

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_fragment, container, false)

        val recipesMaster = RecipesMaster()
        val availableDishes = recipesMaster.getAvailableDishes(Data.dishes, Data.checked)
        Data.availableDishes = availableDishes

//        val adapter = MyAdapter(supportFragmentManager)

        val viewPager = view.findViewById<ViewPager>(R.id.viewpager)
        val striped = view.findViewById<PagerTitleStrip>(R.id.pager_title_strip)
        val dishPagerAdapter = DishPagerAdapter((activity as MainActivity).supportFragmentManager, requireContext(), availableDishes)
        viewPager.adapter = dishPagerAdapter
//        viewPager.adapter = adapter
        viewPager.currentItem = 1

        return view
    }


}