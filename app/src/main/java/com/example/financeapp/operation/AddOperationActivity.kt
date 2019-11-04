package com.example.financeapp.operation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.financeapp.R
import com.example.financeapp.category.CategoriesAdapter
import com.example.financeapp.category.Category
import com.example.financeapp.core.Money
import com.example.financeapp.core.RUB
import com.example.financeapp.util.EXTRA_OPERATION
import com.example.financeapp.util.EXTRA_OPERATION_ID
import kotlinx.android.synthetic.main.activity_add_operation.*

class AddOperationActivity : AppCompatActivity() {
    private var dsCategories : ArrayList<Category> = arrayListOf()
    private var operationId : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_operation)

        operationId = intent.getIntExtra(EXTRA_OPERATION_ID, 0)

        dsCategories = initCategories()

        with(rvCategories) {
            layoutManager = LinearLayoutManager(this@AddOperationActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = CategoriesAdapter(dsCategories)
        }

        btnSave.setOnClickListener {
            Intent().also {
                it.putExtra(EXTRA_OPERATION, Operation(
                    // todo default currency setting
                    Money(amount = amount.text.toString().toDouble(), currency = RUB()),
                    //todo implement category selection
                    Category("Temp Category"),
                    comment.text.toString()
                    )
                )
                setResult(Activity.RESULT_OK, it)
            }
            finish()
        }

        if (operationId > 0) {
            // todo fill operation fields by ID
//            amount.text = dsOperations[operationId].amount.toString()
        }

        amount.requestFocus()
    }

    private fun initCategories(): ArrayList<Category> {
        return (1..10).map {
            Category(getString(R.string.category) + " " + it)
        } as ArrayList
    }
}
