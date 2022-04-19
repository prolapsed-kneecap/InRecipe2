package com.example.inrecipe.data

data class Dish(
    val index : Int,
    val name : String,
    val ingredients : Set<IngredientEnum>
)
