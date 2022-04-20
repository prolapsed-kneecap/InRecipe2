package com.example.inrecipe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.inrecipe.R
import com.example.inrecipe.data.Dish


class SearchAdapter(courseModalArrayList: MutableList<Dish>, context: Context) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    // creating a variable for array list and context.
    private var courseModalArrayList: MutableList<Dish>
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
        holder.imageView.setImageResource(R.drawable.apple_pie)
        holder.textView.text = modal.name
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
