package com.example.financeapp.operation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.financeapp.R
import com.example.financeapp.category.CategoriesAdapter
import com.example.financeapp.category.Category
import kotlinx.android.synthetic.main.activity_add_operation.*

class AddOperationActivity : AppCompatActivity() {
    private lateinit var dsCategories : ArrayList<Category>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_operation)

        initCategories()

        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = CategoriesAdapter(dsCategories)
    }

    private fun initCategories() {
        dsCategories = Array(10) { i ->
            Category
                .create()
                .setName("Category $i")
        }.toCollection(ArrayList())
    }
}
