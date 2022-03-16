package kr.co.smartsoft.keepthetime_t_20220311.adapters

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.smartsoft.keepthetime_t_20220311.datas.UserData

class AppointListRecyclerAdapter(
    val mContext : Context,
    val mList : List<UserData>
) : RecyclerView.Adapter<RequestUserRecyclerAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RequestUserRecyclerAdapter.MyViewHolder {


    }

    override fun onBindViewHolder(holder: RequestUserRecyclerAdapter.MyViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {

    }
}