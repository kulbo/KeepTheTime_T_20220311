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
import kr.co.smartsoft.keepthetime_t_20220311.datas.UserData

class SearchedUserRecyclerAdapter(
    val mConText: Context,
    val mList: List<UserData>
) : RecyclerView.Adapter<SearchedUserRecyclerAdapter.MyViewHolder>()  {

    inner class MyViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        val imgProfile = view.findViewById<ImageView>(R.id.imgProfile)
        val txtNickname = view.findViewById<TextView>(R.id.txtNickname)
        val imgSocialLoginLog = view.findViewById<ImageView>(R.id.imgSocialLoginLogo)
        val txtEmail = view.findViewById<TextView>(R.id.txtEmail)
        val btnAddFriend = view.findViewById<TextView>(R.id.btnAddFriend)

        fun bind(data:UserData) {
            txtNickname.text = data.nick_name
            txtEmail.text = data.email
            Glide.with(mConText).load(data.profile_img).into(imgProfile)
            when(data.provider) {
                "default" -> {
                    imgSocialLoginLog.visibility = View.GONE
                    txtEmail.text = data.email
                }
                "kakao" -> {
                    imgSocialLoginLog.visibility = View.VISIBLE
                    //imgSocialLoginLog.setImageResource(R.drawable)
                    txtEmail.text = "카카오로그인"
                }
                "fasebook" -> {
                    imgSocialLoginLog.visibility = View.VISIBLE
                    //imgSocialLoginLog.setImageResource(R.drawable)
                    txtEmail.text = "페북 로그인"
                }
                "naver" -> {
                    imgSocialLoginLog.visibility = View.VISIBLE
                    //imgSocialLoginLog.setImageResource(R.drawable)
                    txtEmail.text = "네이버로그인"
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

//        xml을 infalte해와 => 이를 가지고 MyViewHoder 객체로
//        재사용성을 알아서 보존해줌.

        val row = LayoutInflater.from(mConText).inflate(R.layout.searched_user_list_item, parent, false)

        return MyViewHolder( row )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

//        실제 데이터 반영 함수.
        val data = mList[position]
//        이 함수에서 직접 코딩하면 => holder.UI 변수로 매번 holder 단어를 쎠야함
//        holder 변수

    }

//    몇개의 아이템을 보여줄 예정인가? => 데이터목록의 갯수만큼.
    override fun getItemCount() = mList.size
}