package com.example.inrecipe.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.inrecipe.data.IngredientEnum
import com.example.inrecipe.R
import com.example.inrecipe.data.Data

class CustomAdapter(private val dataSet: Array<IngredientEnum>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.info_text)
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        var isChosen = false

        Data.checked.forEach {
            if (it == dataSet[position]) {
                isChosen = true
            }
        }

        if (isChosen.not()){
            viewHolder.itemView.alpha = 0.5F
        }
        viewHolder.imageView.setImageResource(dataSet[position].image)

        viewHolder.textView.text = dataSet[position].ingredientName

        viewHolder.itemView.setOnClickListener {
            if (isChosen) {
                it.alpha = 0.5F
                Data.checked.remove(dataSet[position])
            } else {
                it.alpha = 1F
                Data.checked.add(dataSet[position])
            }
            isChosen = isChosen.not()
        }
    }

    override fun getItemCount() = dataSet.size

}