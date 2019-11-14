package com.example.financeapp.operation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.financeapp.BaseFragment
import com.example.financeapp.R
import com.example.financeapp.category.Category
import com.example.financeapp.core.Money
import com.example.financeapp.core.RUB
import com.example.financeapp.util.*
import kotlinx.android.synthetic.main.activity_add_operation.*
import kotlinx.android.synthetic.main.fragment_operations.*
import java.math.BigDecimal


class OperationsFragment : BaseFragment() {
    private var dsOperations : MutableList<Operation> = arrayListOf()

    companion object {
        fun newInstance(): OperationsFragment {
            return OperationsFragment()
        }
    }

    override fun layoutId(): Int = R.layout.fragment_operations

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dsOperations = initOperations()

        with(rvOperations) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = OperationsAdapter(dsOperations).also {
                it.setOnClickListener { view ->
                    Intent(view.context, AddOperationActivity::class.java).also { intent ->
                        //todo pass the position to edit operation activity
//                        intent.putExtra(EXTRA_OPERATION_ID, position)
                        requireContext().startActivity(intent)
                    }
                }
            }

            // сначала сделал через кастомный FabBehavior - но там нужен ряд костылей:
            // 1. onNestedScroll не вызывается второй раз, т.к. вызов hide() ставит visibility = GONE, поэтому
            // 2. приходится в hide() передавать обработчик события видимости и вручную выставлять visibility = INVISIBLE
            // 3. выставление visibility напрямую запрещено, поэтмоу приходится еще использовать аннотацию @SuppressLint("RestrictedApi")
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
            Intent(activity, AddOperationActivity::class.java).also { intent ->
                startActivityForResult(intent, ADD_OPERATION_REQUEST)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == ADD_OPERATION_REQUEST) {
                data?.extras?.getParcelable<Operation>(EXTRA_OPERATION)?.let {
                    (rvOperations.adapter as OperationsAdapter).addItem(it)
                    rvOperations.scrollToPosition(0)
                }
            }
        }
    }

    private fun initOperations(): MutableList<Operation> {
        return (1..30).map {
            Operation(
                amount = Money(
                    amount = BigDecimal(10 * it),
                    currency = RUB),
                category = Category(getString(R.string.category)),
                comment = getString(R.string.comment))
        } as ArrayList
    }
}
