package com.example.inrecipe.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.inrecipe.R
import com.example.inrecipe.data.Data
import com.example.inrecipe.data.Dish
import com.example.inrecipe.ui.activity.SearchActivity


class SearchAdapter(courseModalArrayList: List<Dish>, context: Context) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    // creating a variable for array list and context.
    private var courseModalArrayList: List<Dish>
    private val context: Context

    fun filterList(filterllist: MutableList<Dish>) {
        courseModalArrayList = filterllist
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.favorite_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val modal: Dish = courseModalArrayList[position]
        holder.imageView.setImageResource(modal.image)
        holder.textView.text = modal.name
        holder.imageView.setOnClickListener {
            (context as SearchActivity).inspectRecipe(Data.dishes.indexOf(modal))
        }
    }

    override fun getItemCount(): Int {
        return courseModalArrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.recipeImageView)
        val textView: TextView = itemView.findViewById(R.id.recipeName)
    }

    init {
        this.courseModalArrayList = courseModalArrayList
        this.context = context
    }
}
