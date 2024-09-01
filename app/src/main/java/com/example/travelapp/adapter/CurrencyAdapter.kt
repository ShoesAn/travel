package com.example.travelapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapp.R
import com.example.travelapp.model.MoneyCurrency
import com.example.travelapp.model.ScheduleModel
import com.example.travelapp.model.ScheduleModel.FlightInfo

class CurrencyAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    private lateinit var currencyData: MoneyCurrency

    fun setCurrency(currency: MoneyCurrency){
        currencyData = currency
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.currency_item_view, parent, false)
        return CurrencyItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is CurrencyItemViewHolder) {
            holder.bindData(currencyData, position)
        }
    }

    class CurrencyItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val currencyName: TextView = itemView.findViewById(R.id.currencyName)
        val currencyNum: TextView = itemView.findViewById(R.id.currencyNum)

        fun bindData(item: MoneyCurrency, position: Int) {
            when(position){
                0 ->  {
                    currencyName.text = "AUD"
                    currencyNum.text = item.AUD
                }
                1 ->  {
                    currencyName.text = "CNY"
                    currencyNum.text = item.CNY
                }
                2 ->  {
                    currencyName.text = "EUR"
                    currencyNum.text = item.EUR
                }
                3 ->  {
                    currencyName.text = "JPY"
                    currencyNum.text = item.JPY
                }
                4 ->  {
                    currencyName.text = "KRW"
                    currencyNum.text = item.KRW
                }
                5 ->  {
                    currencyName.text = "USD"
                    currencyNum.text = item.USD
                }
            }
        }
    }
}