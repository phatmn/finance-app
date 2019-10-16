package com.example.financeapp.operation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.financeapp.R
import com.example.financeapp.category.CategoriesAdapter
import com.example.financeapp.category.Category
import kotlinx.android.synthetic.main.activity_add_operation.*

class AddOperationActivity : AppCompatActivity() {
    private val dsCategories : ArrayList<Category> = initCategories()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_operation)

        with(rvCategories) {
            layoutManager = LinearLayoutManager(this@AddOperationActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = CategoriesAdapter(dsCategories)
        }
    }

    private fun initCategories(): ArrayList<Category> {
        return (1..10).map {
            Category("Category $it")
        } as ArrayList
    }
}
