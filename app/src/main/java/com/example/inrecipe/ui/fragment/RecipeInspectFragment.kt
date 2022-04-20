package com.example.inrecipe.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.recipe_inspect_fragment, container, false)

        if (arguments?.getInt("id") != null) {
            Data.dishes.forEach {
                if (it.index == arguments!!.getInt("id")) {
                    view.findViewById<TextView>(R.id.dishTitle).text = it.name
                    view.findViewById<ImageView>(R.id.dishImage).setImageResource(Data.mTopImageResourceIds[it.index])
                    var ings = ""
                    it.ingredients.forEach { ing ->
                        ings += "${ing.ingredientName} "
                    }
                    view.findViewById<TextView>(R.id.dishDescription).text = ings
                }
            }
        }

        return view
    }

}