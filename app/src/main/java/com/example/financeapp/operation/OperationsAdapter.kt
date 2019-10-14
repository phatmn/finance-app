package com.example.financeapp.operation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.financeapp.R
import kotlinx.android.synthetic.main.operation.view.*

class OperationsAdapter(private val operations: ArrayList<Operation>) :
    RecyclerView.Adapter<OperationsAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val amount: TextView = v.oper_amount
        val category: TextView = v.oper_category
        val comment: TextView = v.oper_comment
        val image: ImageView = v.oper_image
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
        holder.amount.text = operations[position].amount.toString()
        holder.category.text = operations[position].category
        holder.comment.text = operations[position].comment
        holder.image.setImageResource(R.drawable.ic_local_grocery_store_black_24dp)
    }


}
