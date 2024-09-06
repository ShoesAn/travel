package com.example.travelapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapp.R
import com.example.travelapp.model.ScheduleModel
import com.example.travelapp.model.ScheduleModel.FlightInfo

class FlightAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var planeInfoList: List<FlightInfo>? = ArrayList()

    fun setPlaneInfo(list: List<FlightInfo>?) {
        planeInfoList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.flight_item_view, parent, false)
        return FlightItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return planeInfoList?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FlightItemViewHolder) {
            planeInfoList?.let { holder.bindData(it.get(position)) }
        }
    }

    class FlightItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val expectNumText: TextView = itemView.findViewById(R.id.expectNumText)
        val realNumText: TextView = itemView.findViewById(R.id.realNumText)
        val flightNumText: TextView = itemView.findViewById(R.id.flightNumText)
        val gateText: TextView = itemView.findViewById(R.id.gateText)
        val statusText: TextView = itemView.findViewById(R.id.statusText)
        val airLineCode: TextView = itemView.findViewById(R.id.airLineCode)
        val airLineName: TextView = itemView.findViewById(R.id.airLineName)
        val upAirportCode: TextView = itemView.findViewById(R.id.upAirportCode)
        val upAirportName: TextView = itemView.findViewById(R.id.upAirportName)

        fun bindData(item: FlightInfo) {
            expectNumText.text = item.expectTime
            realNumText.text = item.realTime
            flightNumText.text = item.airLineNum
            gateText.text = item.airBoardingGate
            airLineCode.text = item.airLineCode
            airLineName.text = item.airLineName
            upAirportCode.text = item.upAirportCode
            upAirportName.text = item.upAirportName
            statusText.text = item.airFlyStatus
        }
    }
}