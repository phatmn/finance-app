package com.example.financeapp.account

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.financeapp.BaseFragment
import com.example.financeapp.R
import com.example.financeapp.core.Money
import com.example.financeapp.core.RUB
import com.example.financeapp.util.ADD_ACCOUNT_REQUEST
import com.example.financeapp.util.EXTRA_ACCOUNT
import kotlinx.android.synthetic.main.fragment_accounts.*

class AccountsFragment : BaseFragment() {
    private var dsAccounts : ArrayList<Account> = arrayListOf()

    companion object {
        fun newInstance() : AccountsFragment {
            return AccountsFragment()
        }
    }

    override fun layoutId(): Int = R.layout.fragment_accounts

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dsAccounts = initAccounts()

        with(rvAccounts) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = AccountsAdapter(dsAccounts).also {
                it.setOnClickListener(View.OnClickListener { view ->
                    Intent(view?.context, AddAccountActivity::class.java).also { intent ->
                        //todo pass the position to edit operation activity
//                        intent.putExtra(EXTRA_OPERATION_ID, position)
                        (view?.context as Activity).startActivity(intent)
                    }
                })
            }

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0 && fab.visibility == View.VISIBLE) {
                        fab.hide()
                    } else if (dy < 0 && fab.visibility != View.VISIBLE) {
                        fab.show()
                    }
                }
            })
        }

        fab.setOnClickListener {
            Intent(activity, AddAccountActivity::class.java).also { intent ->
                startActivityForResult(intent, ADD_ACCOUNT_REQUEST)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == ADD_ACCOUNT_REQUEST) {
                data?.extras?.getParcelable<Account>(EXTRA_ACCOUNT)?.let {
                    (rvAccounts.adapter as AccountsAdapter).addItem(it)
                    rvAccounts.scrollToPosition(0)
                }
            }
        }
    }

    private fun initAccounts(): java.util.ArrayList<Account> {
        return (1..5).map {
            Account(
                name = getString(R.string.account) + it,
                type = Card,
                amount = Money(
                    amount = 10 * it.toDouble(),
                    currency = RUB()
                )
            )
        } as ArrayList
    }
}
