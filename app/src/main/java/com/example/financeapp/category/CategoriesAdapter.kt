package com.example.financeapp.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.financeapp.R
import kotlinx.android.synthetic.main.category.view.*

class CategoriesAdapter(private val categories: List<Category>) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    var selectedPos: Int = RecyclerView.NO_POSITION

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val name: TextView = v.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            name.text = categories[position].name
            itemView.isSelected = (selectedPos == position)
            itemView.setOnClickListener {
                notifyItemChanged(selectedPos)
                selectedPos = layoutPosition
                notifyItemChanged(selectedPos)
            }
        }
    }
}
