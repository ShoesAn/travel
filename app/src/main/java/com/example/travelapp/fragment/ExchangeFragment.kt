package com.example.travelapp.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelapp.adapter.CurrencyAdapter
import com.example.travelapp.databinding.ExchangeFragmentBinding
import com.example.travelapp.viewmodel.CurrencyViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ExchangeFragment : Fragment() {
    private var _binding: ExchangeFragmentBinding? = null
    private val binding get() = _binding!!
    private val currencyViewModel: CurrencyViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = ExchangeFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currencyViewModel.currencyInfoList.observe(viewLifecycleOwner) {
            if (it != null) {
                val currencyAdapter = CurrencyAdapter()
                currencyAdapter.setCurrency(it)
                binding.changeRecycle.adapter = currencyAdapter
                binding.changeRecycle.layoutManager = LinearLayoutManager(context)
            } else {
                AlertDialog.Builder(context).setMessage("Get currency failed").create()
            }
        }

        val timer = object : CountDownTimer(3 * 60 * 1000, 3 * 60 * 1000) {
            override fun onTick(millisUntilFinished: Long) {
                currencyViewModel.getCurrencyInfo(null)
            }

            override fun onFinish() {
                start()
            }
        }
        timer.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}