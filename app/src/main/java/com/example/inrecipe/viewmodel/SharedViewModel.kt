package com.example.inrecipe.viewmodel

import androidx.lifecycle.ViewModel
import com.example.inrecipe.data.Data
import com.example.inrecipe.data.Dish
import com.example.inrecipe.data.RecipesMaster

class SharedViewModel : ViewModel() {
    val recipesMaster: RecipesMaster = RecipesMaster()
    fun getAvailableRecipes(): MutableList<Dish> {
        return recipesMaster.getAvailableDishes(Data.dishes, Data.checked)
    }
}