package com.example.financeapp.account

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.financeapp.R
import kotlinx.android.synthetic.main.account.view.*

class AccountsAdapter(private val accounts: MutableList<Account>) :
    RecyclerView.Adapter<AccountsAdapter.ViewHolder>() {

    private var listener: View.OnClickListener? = null

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val parentView: View = v
        val name: TextView = v.name
        val amount: TextView = v.amount
        val type: ImageView = v.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.account, parent, false)

        return ViewHolder(v)
    }

    override fun getItemCount() = accounts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // todo account type change
        with(holder) {
            amount.text = accounts[position].amount.toString()
            name.text = accounts[position].name
            type.setImageResource(R.drawable.ic_payment_black_24dp)
            parentView.setOnClickListener(listener)
        }
    }

    fun addItem(account: Account) {
        accounts.add(0, account)
        this.notifyItemInserted(0)
    }

    fun setOnClickListener(listener: View.OnClickListener) {
        this.listener = listener
    }
}