package com.example.inrecipe.data

import com.example.inrecipe.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object Data {

    var uid = ""

    val checked = mutableSetOf<IngredientEnum>(
    )

    var availableDishes = listOf<Dish>()

    val database = Firebase.firestore

    var favorites = mutableSetOf<Int>(

    )

    var mTopImageResourceIds = intArrayOf(
        R.drawable.fruit_salad,
        R.drawable.salad,
        R.drawable.omelette,
        R.drawable.apple_pie,
        R.drawable.chicken_soup,
        R.drawable.tart,
        R.drawable.chicken_soup,
        R.drawable.chicken_soup,
    )

    val dishes = listOf<Dish>(
        Dish(
            1,
            "Фруктовый салат",
            setOf(
                IngredientEnum.APPLE,
                IngredientEnum.TANGERINE,
                IngredientEnum.STRAWBERRY
            ),
            R.drawable.fruit_salad,
            R.string.recipe_fruit_salad
        ),
        Dish(
            2,
            "Салат",
            setOf(
                IngredientEnum.TOMATO,
                IngredientEnum.PEPPER
            ),
            R.drawable.salad,
            R.string.recipe_salad
        ),
        Dish(
            3,
            "Яичница",
            setOf(
                IngredientEnum.EGGS
            ),
            R.drawable.omelette,
            R.string.recipe_omelette
        ),
        Dish(
            4,
            "Яблочный пирог",
            setOf(
                IngredientEnum.APPLE,
                IngredientEnum.FLOUR,
                IngredientEnum.MILK,
                IngredientEnum.EGGS
            ),
            R.drawable.apple_pie,
            R.string.recipe_apple_pie
        ),
        Dish(
            5,
            "Куриный суп с рулетиками",
            setOf(
                IngredientEnum.CHICKEN,
                IngredientEnum.BUTTER,
                IngredientEnum.EGGS,
                IngredientEnum.FLOUR,
            ),
            R.drawable.chicken_soup,
            R.string.recipe_chicken_soup
        ),
        Dish(
            6,
            "Тарт Клубника со сливками",
            setOf(
                IngredientEnum.BUTTER,
                IngredientEnum.EGGS,
                IngredientEnum.FLOUR,
            ),
            R.drawable.tart,
            R.string.recipe_tart
        ),
    )

}