package com.example.financeapp.operation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.financeapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

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
    }
}
