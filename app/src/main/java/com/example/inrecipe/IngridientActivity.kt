package com.example.inrecipe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class IngridientActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingridient)

//        Data.checked.clear()
//
//        val recylerView = findViewById<RecyclerView>(R.id.recyclerView)
//        val button: Button = findViewById(R.id.button)
//
//
//        recylerView.adapter = CustomAdapter(IngredientEnum.values())
//        val layoutManager = GridLayoutManager(this, 2)
//        recylerView.layoutManager = layoutManager
//
//        button.setOnClickListener {
//            if (Data.checked.isNotEmpty()) {
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
//            } else {
//                val builder = AlertDialog.Builder(this, R.style.AlertDialogStyle)
//                builder.setTitle("Выберите хотя бы 1 ингредиент.")
//                builder.setPositiveButton(
//                    "Ok"
//                ) { p0, p1 -> }
//                builder.create()
//                builder.show()
//            }
//        }
    }
}