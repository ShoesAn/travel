package com.example.travelapp.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelapp.R
import com.example.travelapp.adapter.FlightAdapter
import com.example.travelapp.databinding.AirplaneFragmentBinding
import com.example.travelapp.viewmodel.AirplaneViewModel
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AirplaneFragment : Fragment() {

    private var _binding: AirplaneFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val airplaneViewModel : AirplaneViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AirplaneFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        airplaneViewModel.planeInfoList.observe(viewLifecycleOwner){
            if(it != null){
                val flightAdapter = FlightAdapter()
                flightAdapter.setPlaneInfo(it)
                binding.flightRecycle.adapter = flightAdapter
                binding.flightRecycle.layoutManager = LinearLayoutManager(context)
            } else {
                AlertDialog.Builder(context).setMessage("File open failed").create()
            }
        }
        airplaneViewModel.getAirplaneInfo()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}