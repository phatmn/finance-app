package com.example.financeapp.operation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.financeapp.R
import kotlinx.android.synthetic.main.operation.view.*

class OperationsAdapter(private val operations: MutableList<Operation>) :
    RecyclerView.Adapter<OperationsAdapter.ViewHolder>() {

    private var listener: View.OnClickListener? = null

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val parentView: View = v
        val amount: TextView = v.operAmount
        val category: TextView = v.operCategory
        val comment: TextView = v.operComment
        val image: ImageView = v.operImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.operation, parent, false)

        return ViewHolder(v)
    }

    override fun getItemCount() = operations.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // todo image category change
        // todo category change
        with(holder) {
            amount.text = operations[position].amount.toString()
            category.text = operations[position].category.name
            comment.text = operations[position].comment
            image.setImageResource(R.drawable.ic_local_grocery_store_black_24dp)
            parentView.setOnClickListener(listener)
        }
    }

    fun addItem(operation: Operation) {
        operations.add(0, operation)
        this.notifyItemInserted(0)
    }

    fun setOnClickListener(listener: View.OnClickListener) {
            this.listener = listener
    }
}
