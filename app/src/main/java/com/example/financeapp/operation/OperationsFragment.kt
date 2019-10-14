package com.example.financeapp.operation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.financeapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar


class OperationsFragment : Fragment() {
    private lateinit var dsOperations : ArrayList<Operation>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initOperations()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_operations, container, false)
        val activity = activity as Context

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvOperations)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = OperationsAdapter(dsOperations)

        view.findViewById<FloatingActionButton>(R.id.floating_action_button).setOnClickListener { view ->
            startActivity(Intent(activity, AddOperationActivity::class.java))
        }
        
        return view
    }

    private fun initOperations() {
        dsOperations = Array(30) { i ->
            Operation
                .create()
                .setAmount(10 * i.toDouble())
        }.toCollection(ArrayList())
    }

    companion object {

        @JvmStatic
        fun newInstance(): OperationsFragment {
            return OperationsFragment()
        }
    }
}
