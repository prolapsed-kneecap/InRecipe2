package com.example.inrecipe.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.inrecipe.data.Data
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

import androidx.appcompat.view.menu.MenuBuilder
import com.example.inrecipe.R


class MainActivity : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth
    private lateinit var activity : MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        supportActionBar?.hide()

        supportActionBar?.title = ""
        supportActionBar?.setBackgroundDrawable(resources.getDrawable(R.color.orange))


        val bnv = findViewById<BottomNavigationView>(R.id.bnv)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val navController = navHostFragment!!.findNavController()
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.ingredientFragment,
                R.id.mainFragment,
                R.id.favoritesFragment2
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        bnv.setupWithNavController(navController)

        mAuth = FirebaseAuth.getInstance()
//        mAuth.signOut()

        Data.database.collection("users").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if (document.get("id") == mAuth.currentUser?.uid.toString()) {
                        Data.favorites = (document.get("favorites") as Array<Int>).toMutableList()
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w("BBB", "Error getting documents.", exception)
            }

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)

//        if (menu is MenuBuilder) {
//            menu.setOptionalIconsVisible(true)
//        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.ingredientFragment -> {
                mAuth.signOut()
                startActivity(Intent(this, AuthActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

//        Toast.makeText(this, availableDishes.toString(), Toast.LENGTH_SHORT).show()
//        availableDishes.forEach {
//            Log.d("AAA", it.name)
//        }
//        Log.d("BBB", availableDishes.toString())
}