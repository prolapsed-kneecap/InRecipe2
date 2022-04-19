package com.example.inrecipe

data class Dish(
    val index : Int,
    val name : String,
    val ingredients : Set<IngredientEnum>
)
