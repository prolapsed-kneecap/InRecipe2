package com.example.inrecipe

object Data {
    val dishes = mutableSetOf<Dish>(
        Dish(
            "Салат из яблок и мандаринов",
            setOf(
                IngredientEnum.APPLE,
                IngredientEnum.TANGERINE
            )
        ),
        Dish(
            "Томатное нечто",
            setOf(
                IngredientEnum.TOMATO,
            )
        ),
        Dish(
            "Яичница",
            setOf(
                IngredientEnum.EGGS
            )
        ),
        Dish(
            "Яблочный пирог",
            setOf(
                IngredientEnum.APPLE,
                IngredientEnum.FLOUR,
                IngredientEnum.MILK,
                IngredientEnum.EGGS
            )
        ),
    )
    val checked = mutableSetOf<IngredientEnum>(
    )

    var availableDishes = listOf<Dish>()
}