package kr.co.smartsoft.keepthetime_t_20220311.adapters

import android.content.Context
import android.service.autofill.UserData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.smartsoft.keepthetime_t_20220311.R

class SearchedUserRecyclerAdapter(
    val mConText: Context,
    val mList: List<UserData>
) : RecyclerView.Adapter<SearchedUserRecyclerAdapter.MyViewHolder>()  {

    inner class MyViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

//        xml을 infalte해화 => 이를 가지고 MyViewHoder 객체로
//        재사용성을 알아서 보존해줌.
        val row = LayoutInflater.from(mConText).inflate(R.layout.searched_user_list_item, parent, false)

        return MyViewHolder( row )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

//    몇개의 아이템을 보여줄 예정인가? => 데이터목록의 갯수만큼.
    override fun getItemCount() = mList.size
}