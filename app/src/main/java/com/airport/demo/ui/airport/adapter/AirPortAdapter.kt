package com.airport.demo.ui.airport.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airport.demo.R
import com.airport.demo.databinding.AdapterAirpoartInfoBinding
import com.airport.demo.model.AirPortFlyEntity

class AirPortAdapter(private var info:List<AirPortFlyEntity>) :RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var page = 1
    private var pageNum = 50

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val _binding = AdapterAirpoartInfoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MViewHolder(_binding)
    }

    override fun getItemCount(): Int {
      return info.take(page.times(pageNum)).size
    }


    fun update(data : List<AirPortFlyEntity>){
        this.info = data
        notifyDataSetChanged()
    }

    fun checkLoadMore(lastPosition: Int){
        if (lastPosition == info.take(page.times(pageNum)).size-1 && lastPosition != info.size-1){
            page++
            notifyDataSetChanged()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as MViewHolder
        holder.bind(info[position])
    }




    class MViewHolder(private val _binding: AdapterAirpoartInfoBinding) : RecyclerView.ViewHolder(_binding.root) {
        @SuppressLint("StringFormatMatches")
        fun bind(info: AirPortFlyEntity) {
            val c = _binding.root.context
            _binding.estimateTime.text = info.EstimatedTime
            _binding.realTime.text = info.ActualTime
            val flightNum = c.getString(R.string.flight_num,info.FlightNumber)
            _binding.flightNumber.text = flightNum
            val tvReMarker = _binding.remark
            tvReMarker.text = info.Remark
            info.Remark?.let {
                if (it.contains("CANCEL")){
                    tvReMarker.setTextColor(c.getColor(R.color.red))
                }else if (it.contains("CHANGE")){
                    tvReMarker.setTextColor(c.getColor(R.color.orange))
                }else{
                    tvReMarker.setTextColor(c.getColor(R.color.green))
                }
            }
            val flightDes = c.getString(
                R.string.flight_des,
                info.DepartureAirportID,
                info.DepartureAirport,
                info.ArrivalAirportID,
                info.ArrivalAirport)

            _binding.flightPositionDes.text = flightDes
            val terminal = c.getString(R.string.terminal_gate,info.Terminal,info.Gate)
            _binding.terminal.text = terminal
        }
    }
}