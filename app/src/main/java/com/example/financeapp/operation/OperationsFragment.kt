package com.example.financeapp.operation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.financeapp.R
import kotlinx.android.synthetic.main.fragment_operations.*


class OperationsFragment : Fragment() {
    private var dsOperations : ArrayList<Operation> = initOperations()

    companion object {
        fun newInstance(): OperationsFragment {
            return OperationsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_operations, container, false)

        val operations = view.findViewById<RecyclerView>(R.id.rvOperations)
        with(operations) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = OperationsAdapter(dsOperations)
        }

        fab?.setOnClickListener {
            startActivity(Intent(activity, AddOperationActivity::class.java))
        }
        
        return view
    }

    private fun initOperations(): java.util.ArrayList<Operation> {
        return (1..30).map {
            Operation(10 * it.toDouble())
        } as ArrayList
    }
}
