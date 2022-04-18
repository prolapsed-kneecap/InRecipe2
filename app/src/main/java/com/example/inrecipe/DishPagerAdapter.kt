package com.example.inrecipe

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class DishPagerAdapter internal constructor(
    fm: FragmentManager?,
    context: Context,
    dishes: List<Dish>
) :
    FragmentPagerAdapter(fm!!) {
    private lateinit var mDishNames: MutableList<String>
    private lateinit var mDishDescriptions: MutableList<String>
    private var mTopImageResourceIds = intArrayOf(
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_background
    )

    override fun getItem(position: Int): Fragment {
        val arguments = Bundle()
        arguments.putString(DishFragment.CAT_NAMES, mDishNames[position])

        arguments.putString(
            DishFragment.CAT_DESCRIPTIONS,
            mDishDescriptions[position]
        )
        arguments.putInt(DishFragment.TOP_IMAGE, mTopImageResourceIds[position])
        val catsFragment = DishFragment()
        catsFragment.arguments = arguments
        return catsFragment
    }

    override fun getCount(): Int {
        return mDishNames.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mDishNames[position]
    }

    init {
        val resources = context.resources
        mDishNames = mutableListOf()
        mDishDescriptions = mutableListOf()
        dishes.forEach {
            mDishNames.add(it.name)
            var ingredientString = ""
            it.ingredients.forEach {
                ingredientString += "${it.ingredientName} "
            }
            mDishDescriptions.add(ingredientString)
        }
    }
}