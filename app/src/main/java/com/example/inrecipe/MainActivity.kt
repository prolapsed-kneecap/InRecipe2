package com.example.inrecipe

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerTitleStrip
import androidx.viewpager.widget.ViewPager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       /* val recipesMaster = RecipesMaster()
        val availableDishes = recipesMaster.getAvailableDishes(Data.dishes, Data.checked)
        Data.availableDishes = availableDishes

//        val adapter = MyAdapter(supportFragmentManager)

        val viewPager = findViewById<ViewPager>(R.id.viewpager)
        val striped = findViewById<PagerTitleStrip>(R.id.pager_title_strip)
        val dishPagerAdapter = DishPagerAdapter(supportFragmentManager, this, availableDishes)
        viewPager.adapter = dishPagerAdapter
//        viewPager.adapter = adapter
        viewPager.currentItem = 1*/
    }

//        Toast.makeText(this, availableDishes.toString(), Toast.LENGTH_SHORT).show()
//        availableDishes.forEach {
//            Log.d("AAA", it.name)
//        }
//        Log.d("BBB", availableDishes.toString())
}