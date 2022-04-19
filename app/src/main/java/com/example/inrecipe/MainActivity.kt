package com.example.inrecipe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val bnv = findViewById<BottomNavigationView>(R.id.bnv)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val navController = navHostFragment!!.findNavController()
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.ingredientFragment,
                R.id.mainFragment,
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        bnv.setupWithNavController(navController)


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