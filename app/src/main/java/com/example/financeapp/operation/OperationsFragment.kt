package com.example.financeapp.operation

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.financeapp.BaseFragment
import com.example.financeapp.R
import com.example.financeapp.category.Category
import com.example.financeapp.core.InMemoryStorage
import com.example.financeapp.core.Money
import com.example.financeapp.core.RUB
import com.example.financeapp.util.Util
import kotlinx.android.synthetic.main.fragment_operations.*
import java.math.BigDecimal


class OperationsFragment : BaseFragment() {
    companion object {
        fun newInstance(): OperationsFragment {
            return OperationsFragment()
        }
    }

    override fun layoutId(): Int = R.layout.fragment_operations

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

        Util.getFromSharedPrefs<MutableList<Operation>>(
            requireContext(),
            "operations"
        )?.let {
            InMemoryStorage.operations = it
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        if (menu.size() == 0) {
            menu.add("filter")
                .setIcon(R.drawable.ic_filter)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (InMemoryStorage.operations.size == 0)
            InMemoryStorage.operations.addAll(initOperations())

        with(rvOperations) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = OperationsAdapter(InMemoryStorage.operations).also {
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

        InMemoryStorage.registerObserver { action: InMemoryStorage.Action, _: Any? ->
            val adapter = rvOperations.adapter as OperationsAdapter
            when (action) {
                InMemoryStorage.Action.AddOperation -> {
                    adapter.notifyItemInserted(0)
                    rvOperations.scrollToPosition(0)
                }
                InMemoryStorage.Action.Filter -> {
                    adapter.notifyDataSetChanged()
                }
                else -> {
                }
            }
        }

        fab.setOnClickListener {
            Intent(activity, AddOperationActivity::class.java).also {
                startActivity(it)
            }
        }

//        BottomSheetBehavior.from(bottom_sheet).state = BottomSheetBehavior.STATE_HIDDEN
    }

    private fun initOperations(): MutableList<Operation> {
        return (1..30).map {
            Operation(
                value = Money(
                    amount = BigDecimal(10 * it),
                    currency = RUB
                ),
                category = Category(getString(R.string.category)),
                comment = getString(R.string.comment)
            )
        } as ArrayList
    }

    override fun onDestroy() {
        super.onDestroy()
        Util.saveToSharedPrefs(requireContext(), "operations", InMemoryStorage.operations)
    }
}
