package com.example.inrecipe.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.inrecipe.data.IngredientEnum
import com.example.inrecipe.R
import com.example.inrecipe.data.Data
import com.example.inrecipe.data.Dish
import com.example.inrecipe.ui.activity.MainActivity
import com.example.inrecipe.ui.activity.RecipeInspectActivity

class FavoriteAdapter(private val dataSet: MutableList<Dish>, private val activityContext: Context) :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.recipeName)
        val imageView: ImageView = view.findViewById(R.id.recipeImageView)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.favorite_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.setOnClickListener {
            val intent = Intent(activityContext, RecipeInspectActivity::class.java)
            intent.putExtra("position", dataSet[position].index - 1)
            activityContext.startActivity(intent)
//            val bundle = Bundle()
//            bundle.putInt("position", dataSet[position].index - 1)
//            navController.popBackStack()
//            navController.navigate(R.id.mainFragment, bundle)
        }
        viewHolder.imageView.setImageResource(dataSet[position].image)
        viewHolder.textView.text = dataSet[position].name
    }

    override fun getItemCount() = dataSet.size

}