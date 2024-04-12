package com.example.inrecipe.adapter

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.inrecipe.data.Dish
import com.example.inrecipe.ui.fragment.DishFragment


class DishPagerAdapter internal constructor(
    fm: FragmentManager?,
    context: Context,
    dishes: List<Dish>
) :
    FragmentStatePagerAdapter(fm!!) {
    private lateinit var mDishNames: MutableList<String>
    private lateinit var mDishDescriptions: MutableList<String>
    private lateinit var mDishes: List<Dish>

    override fun getItem(position: Int): Fragment {
        val arguments = Bundle()

        arguments.putInt("position", position)
        arguments.putString("description", mDishDescriptions[position])
        arguments.putInt("recipe", mDishes[position].recipe)

        val dishFragment = DishFragment()
        dishFragment.arguments = arguments
        return dishFragment
    }

    override fun getCount(): Int {
        return mDishNames.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mDishNames[position]
    }

    init {
        val resources = context.resources
        mDishNames = mutableListOf()
        mDishDescriptions = mutableListOf()
        mDishes = dishes
        dishes.forEach { dish ->
            mDishNames.add(dish.name)
            var ingredientString = "Ингредиенты: "
            dish.ingredients.forEach {
                ingredientString += "${it.ingredientName} "
            }
            mDishDescriptions.add(ingredientString)
        }
    }
}