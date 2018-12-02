package com.example.woonho.bitflowsimulator.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.woonho.bitflowsimulator.R

class BasedTransactionFragment : Fragment() {
    private val TAG = "BasedTurnFragment"

    companion object {
        fun newInstance(): Fragment {
            BasedTransactionFragment().run {
                return this
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.based_transaction_fragment, container, false)
    }
}