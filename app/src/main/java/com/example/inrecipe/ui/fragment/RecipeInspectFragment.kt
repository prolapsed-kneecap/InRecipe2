package com.example.inrecipe.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.inrecipe.R
import com.example.inrecipe.data.Data
import org.w3c.dom.Text

class RecipeInspectFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.recipe_inspect_fragment, container, false)

        if (arguments?.getInt("position") != null) {
            Log.d("AAA", "AA")
            val index = arguments!!.getInt("position") - 1
            val dish = Data.dishes[index]
            view.findViewById<TextView>(R.id.dishTitle).text = dish.name
            view.findViewById<ImageView>(R.id.dishImage).setImageResource(dish.image)
            var ings = ""
            dish.ingredients.forEach { ing ->
                ings += "${ing.ingredientName} "
            }
            view.findViewById<TextView>(R.id.dishDescription).text = ings
            view.findViewById<TextView>(R.id.recipeText).text = getString(dish.recipe)
        }

        return view
    }
}