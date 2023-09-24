package com.example.inrecipe.data

import com.example.inrecipe.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object Data {
    val dishes = listOf<Dish>(
        Dish(
            1,
            "Фруктовый салат",
            setOf(
                IngredientEnum.APPLE,
                IngredientEnum.TANGERINE,
                IngredientEnum.STRAWBERRY
            ),
            R.drawable.fruit_salad
        ),
        Dish(
            2,
            "Салат",
            setOf(
                IngredientEnum.TOMATO,
                IngredientEnum.PEPPER
            ),
            R.drawable.salad
        ),
        Dish(
            3,
            "Яичница",
            setOf(
                IngredientEnum.EGGS
            ),
            R.drawable.glaz
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
            R.drawable.apple_pie
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
            R.drawable.chicken_soup
        ),
        Dish(
            6,
            "Тарт Клубника со сливками",
            setOf(
                IngredientEnum.CHICKEN,
                IngredientEnum.BUTTER,
                IngredientEnum.EGGS,
                IngredientEnum.FLOUR,
            ),
            R.drawable.tart
        ),
    )
    val checked = mutableSetOf<IngredientEnum>(
    )

    var availableDishes = listOf<Dish>()

    val database = Firebase.firestore

    var favorites = mutableSetOf<Int>(

    )

    var mTopImageResourceIds = intArrayOf(
        R.drawable.fruit_salad,
        R.drawable.salad,
        R.drawable.glaz,
        R.drawable.apple_pie,
        R.drawable.chicken_soup,
        R.drawable.tart,
        R.drawable.chicken_soup,
        R.drawable.chicken_soup,
    )

    val recipes = listOf<String>(
        "Нарезать и перемешать",
        "Нарезать и перемешать",
        "Разбить яйца. Вылить содержимое на сковородку. Ждать. И готово!",
        "К яйцу добавить сахар (70 г), ванильный сахар и соль. Взбить до посветления.\n" +
                "\n" +
                "Добавить молоко, половину растопленного\n" +
                "сливочного масла (20 г) и цедру лимона.\n" +
                "Цедру лимона можно не добавлять, если её нет. Но если у вас есть лимон в холодильнике, то не забудьте добавить этот ингредиент - пирог приобретает просто волшебный аромат.\n" +
                "\n" +
                "Муку смешать с разрыхлителем, добавить в яично-молочную смесь. Перемешать тесто до однородности.\n" +
                "\n" +
                "Яблоки очистить от кожицы (по желанию) и сердцевинок. Нарезать яблоки тонкими дольками.\n" +
                "\n" +
                "Вылить тесто в разъемную форму для выпечки (у меня форма диаметром 21 см), выстеленную пергаментом.\n" +
                "\n" +
                "В тесто утопить дольки яблок вертикально.\n" +
                "\n" +
                "Чем больше яблок уйдет в пирог, тем вкуснее он получится.\n" +
                "\n" +
                "Сверху посыпать сахаром (1-2 ст. ложки) и полить оставшимся растопленным маслом (10-20 г).\n" +
                "\n" +
                "Выпекать яблочный пирог в духовке, заранее прогретой до 180 градусов, 30-40 минут. Готовность пирога проверить деревянной шпажкой, она должна выходить без остатков сырого теста.\n" +
                "Прежде чем доставать пирог из формы, дать ему остыть приблизительно 15 минут.",
        "Куриное мясо разрезать на куски и обжарить на топленом масле до золотистого цвета.\n" +
                "\n" +
                "Когда мясо подрумянится, переложить его в кипящую воду, масло оставить на сковороде.\n" +
                "Бульон посолить по вкусу.\n" +
                "\n" +
                "Половину масла отлить в пиалу и добавить к нему измельченный чеснок.\n" +
                "\n" +
                "Лук и морковь мелко нарезать и обжарить на сковороде с оставшимся маслом,\n" +
                "затем выложить в кастрюлю к бульону.\n" +
                "\n" +
                "Из яиц, щепотки соли и муки замесить тугое тесто, как на лапшу.\n" +
                "\n" +
                "Из полученного теста раскатать две тонкие лепешки толщиной примерно 1 мм, смазать маслом с чесноком, посыпать черным перцем и скрутить рулетики, защипав край, чтобы в процессе варки рулетики не раскрутились.\n" +
                "\n" +
                "Получившиеся рулетики разрезать на кусочки длиной 3 см.\n" +
                "\n" +
                "Когда курица в бульоне будет готова, опустить рулетики в бульон и варить еще минут пять.",
        "",
        "",
        "",
        "",
    )
}