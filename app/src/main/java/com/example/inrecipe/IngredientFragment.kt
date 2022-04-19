package com.example.inrecipe

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class IngredientFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.ingredient_fragment, container, false)

        val recylerView = view.findViewById<RecyclerView>(R.id.recyclerView)
//        val button: Button = view.findViewById(R.id.button)

        recylerView.adapter = CustomAdapter(IngredientEnum.values())
        val layoutManager = GridLayoutManager(requireContext(), 2)
        recylerView.layoutManager = layoutManager

        /*button.setOnClickListener {
            if (Data.checked.isNotEmpty()) {
                findNavController().navigate(R.id.action_ingredientFragment_to_mainFragment)
            } else {
                val builder = AlertDialog.Builder(requireContext(), R.style.AlertDialogStyle)
                builder.setTitle("Выберите хотя бы 1 ингредиент.")
                builder.setPositiveButton(
                    "Ok"
                ) { p0, p1 -> }
                builder.create()
                builder.show()
            }
        }*/

        return view
    }

}