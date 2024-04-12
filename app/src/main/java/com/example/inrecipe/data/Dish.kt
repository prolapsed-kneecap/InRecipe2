package com.example.inrecipe.data

import com.example.inrecipe.R

data class Dish(
    val index : Int,
    val name : String,
    val ingredients : Set<IngredientEnum>,
    var image : Int,
    val recipe : Int = R.string.recipe_omelette,
    var rating : Int = 0
)
