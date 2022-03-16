package kr.co.smartsoft.keepthetime_t_20220311.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.co.smartsoft.keepthetime_t_20220311.R
import kr.co.smartsoft.keepthetime_t_20220311.ViewMapActivity
import kr.co.smartsoft.keepthetime_t_20220311.datas.AppointmentData
import kr.co.smartsoft.keepthetime_t_20220311.datas.UserData
import java.text.SimpleDateFormat

class AppointmentRecyclerAdapter(
    val mContext : Context,
    val mList : List<AppointmentData>,
)
    : RecyclerView.Adapter<AppointmentRecyclerAdapter.MyViewHolder>(
) {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtTitle = view.findViewById<TextView>(R.id.txtTitle)
        val txtDateTime = view.findViewById<TextView>(R.id.txtDateTime)
        val txtPlaceName = view.findViewById<TextView>(R.id.txtPlaceName)
        val imgViewMap = view.findViewById<ImageView>(R.id.imgViewMap)

        fun bind(data : AppointmentData) {

            txtTitle.text = data.title
            txtPlaceName.text = data.place

            val sdf = SimpleDateFormat("yy년 M월 d일 a h시 m분")
            txtDateTime.text = sdf.format(data.datatime)

            imgViewMap.setOnClickListener {
                val myIntent = Intent(mContext, ViewMapActivity::class.java)
                // myIntent.putExtra()
                mContext.startActivity(myIntent)
            }

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