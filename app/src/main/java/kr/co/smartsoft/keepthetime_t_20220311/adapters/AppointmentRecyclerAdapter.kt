package kr.co.smartsoft.keepthetime_t_20220311.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.co.smartsoft.keepthetime_t_20220311.R
import kr.co.smartsoft.keepthetime_t_20220311.datas.AppointmentData
import kr.co.smartsoft.keepthetime_t_20220311.datas.UserData

class AppointmentRecyclerAdapter(
    val mContext : Context,
    val mList : List<AppointmentData>,
)
    : RecyclerView.Adapter<AppointmentRecyclerAdapter.MyViewHolder>(
) {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data : AppointmentData) {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.apointment_list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = mList[position]
        holder.bind(data)

    }

    override fun getItemCount() = mList.size
}