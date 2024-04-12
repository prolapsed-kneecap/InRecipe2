package com.example.inrecipe.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.inrecipe.R
import com.example.inrecipe.data.Data

class RecipeInspectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_inspect)

        supportActionBar?.setBackgroundDrawable(resources.getDrawable(R.color.orange))

        if (intent.getIntExtra("position", -1) != -1) {
            val index = intent.getIntExtra("position", -1)
            val dish = Data.dishes[index]
            supportActionBar?.title = dish.name
            findViewById<TextView>(R.id.dishTitle).text = dish.name
            findViewById<ImageView>(R.id.dishImage).setImageResource(dish.image)
            var ingredients = ""
            dish.ingredients.forEach { ingredient ->
                ingredients += "${ingredient.ingredientName} "
            }
            findViewById<TextView>(R.id.dishDescription).text = ingredients
            findViewById<TextView>(R.id.recipeText).text = getString(dish.recipe)
        }

    }
}