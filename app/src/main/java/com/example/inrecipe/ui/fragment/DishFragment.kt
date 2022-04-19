package com.example.inrecipe.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.inrecipe.R


class DishFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dish, container, false)
        val arguments = arguments
        if (arguments != null) {
            val catName = arguments.getString(CAT_NAMES)
            val catDescription = arguments.getString(CAT_DESCRIPTIONS)
            val topCardResourceId = arguments.getInt(TOP_IMAGE)
            displayValues(view, catName, catDescription, topCardResourceId)
        }
        return view
    }

    private fun displayValues(
        v: View, name: String?,
        catDescription: String?, topCardResourceId: Int
    ) {
        val catNameTextView = v.findViewById<TextView>(R.id.catTitle)
        val catDescriptionTextView = v.findViewById<TextView>(R.id.catDescription)
        val cardImageView = v.findViewById<ImageView>(R.id.topImage)
        catNameTextView.text = name
        catDescriptionTextView.text = catDescription
        cardImageView.setImageResource(topCardResourceId)
    }

    companion object {
        const val CAT_NAMES = "cats_names"
        const val CAT_DESCRIPTIONS = "cat_descriptions"
        const val TOP_IMAGE = "top image"
    }

}