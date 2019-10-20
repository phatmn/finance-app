package com.example.financeapp.operation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.financeapp.BaseFragment
import com.example.financeapp.R
import com.example.financeapp.util.*
import kotlinx.android.synthetic.main.fragment_operations.*


class OperationsFragment : BaseFragment() {
    private var dsOperations : ArrayList<Operation> = initOperations()

    companion object {
        fun newInstance(): OperationsFragment {
            return OperationsFragment()
        }
    }

    override fun layoutId(): Int = R.layout.fragment_operations

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(rvOperations) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = OperationsAdapter(dsOperations)
        }

        fab.setOnClickListener {
            Intent(activity, AddOperationActivity::class.java).also { intent ->
                startActivityForResult(intent, ADD_OPERATION_REQUEST)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_OPERATION_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                data?.extras?.getParcelable<Operation>(EXTRA_OPERATION)?.let { operation ->
                    dsOperations.add(operation)
                    with (rvOperations) {
                        adapter?.itemCount?.minus(1)?.let { position ->
                            adapter?.notifyItemInserted(position)
                            scrollToPosition(position)
                        }
                    }
                }
            }
        }
    }

    private fun initOperations(): java.util.ArrayList<Operation> {
        return (1..30).map {
            Operation(10 * it.toDouble())
        } as ArrayList
    }
}
