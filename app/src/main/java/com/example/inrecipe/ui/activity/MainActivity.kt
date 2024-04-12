package com.example.inrecipe.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.inrecipe.data.Data
import com.google.firebase.auth.FirebaseAuth

import com.example.inrecipe.R


class MainActivity : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = ""
        supportActionBar?.setBackgroundDrawable(resources.getDrawable(R.color.orange))


        val bnv = findViewById<BottomNavigationView>(R.id.bnv)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val navController = navHostFragment!!.findNavController()
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.ingredientFragment,
                R.id.mainFragment,
                R.id.favoritesFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        bnv.setupWithNavController(navController)

        mAuth = FirebaseAuth.getInstance()

        Data.database.collection("users").document(mAuth.currentUser!!.uid).get()
            .addOnSuccessListener { document ->
                    Data.favorites = (document.get("favorites") as List<Int>).toMutableSet()
                }
            .addOnFailureListener {
                Log.d("USERDATA_FAILURE", it.message.toString())
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.ingredientFragment -> {
                mAuth.signOut()
                startActivity(Intent(this, AuthActivity::class.java))
                Data.favorites.clear()
                finish()
                true
            }
            R.id.searchFragment -> {
                startActivity(Intent(this, SearchActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}