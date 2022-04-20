package com.example.inrecipe.data

class RecipesMaster {
    fun getAvailableDishes(
        allDishes: List<Dish>,
        userIngredients: Set<IngredientEnum>
    ): MutableList<Dish> {
        return allDishes.filter {
            val intersection = it.ingredients.intersect(userIngredients)
            intersection.isNotEmpty() && intersection.size == it.ingredients.size
        }.toMutableList()
    }
}