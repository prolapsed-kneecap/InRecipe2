package com.example.inrecipe.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.inrecipe.R
import com.example.inrecipe.data.Data
import com.example.inrecipe.ui.activity.MainActivity


class DishFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dish, container, false)
        val arguments = arguments
        if (arguments != null && Data.availableDishes.isNotEmpty()) {
            var position = arguments.getInt("position")
            if (position >= Data.availableDishes.size)
                position = 0
            val description = arguments.getString("description")
            val recipe = getString(arguments.getInt("recipe"))
            val dish = Data.availableDishes[position]

            displayValues(view, dish.name, description, dish.image, recipe)
        }
        return view
    }

    private fun displayValues(
        v: View, name: String?,
        catDescription: String?, topCardResourceId: Int,
        recipe:String
    ) {
        val dishNameTextView = v.findViewById<TextView>(R.id.dish_title)
        val dishDescriptionTextView = v.findViewById<TextView>(R.id.dish_ingredients)
        val cardImageView = v.findViewById<ImageView>(R.id.dish_image)
        val recipeTextView = v.findViewById<TextView>(R.id.dish_recipe)
        recipeTextView.text = recipe
        dishNameTextView.text = name
        dishDescriptionTextView.text = catDescription
        cardImageView.setImageResource(topCardResourceId)
    }
}