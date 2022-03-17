package kr.co.smartsoft.keepthetime_t_20220311.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kr.co.smartsoft.keepthetime_t_20220311.R
import kr.co.smartsoft.keepthetime_t_20220311.ViewMapActivity
import kr.co.smartsoft.keepthetime_t_20220311.datas.AppointmentData
import kr.co.smartsoft.keepthetime_t_20220311.datas.PlaceData
import java.text.SimpleDateFormat

class MyPlacesRecyclerAdapter(
    val mContext : Context,
    val mList : List<PlaceData>,
)
    : RecyclerView.Adapter<MyPlacesRecyclerAdapter.MyViewHolder>(
) {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val txtStartPlaceName = view.findViewById<TextView>(R.id.txtStartPlanceName)

        fun bind(data : PlaceData) {

            txtStartPlaceName.text = data.name

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.appointment_list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = mList[position]
        holder.bind(data)

    }

    override fun getItemCount() = mList.size
}