package com.example.financeapp.operation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.financeapp.R
import com.example.financeapp.core.InMemoryStorage
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_operations_filter.*

class OperationsFilterFragment : BottomSheetDialogFragment() {
    companion object {
        fun newInstance() = OperationsFilterFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_operations_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnClear.setOnClickListener {
            amountFrom.text.clear()
            amountTo.text.clear()
            applyChanges()
        }

        btnApply.setOnClickListener {
            applyChanges()
        }

        amountFrom.requestFocus()

    }

    private fun applyChanges() {
        InMemoryStorage.filter(
            (amountFrom as TextView).text.toString().toBigDecimalOrNull(),
            (amountTo as TextView).text.toString().toBigDecimalOrNull()
        )
    }
}
