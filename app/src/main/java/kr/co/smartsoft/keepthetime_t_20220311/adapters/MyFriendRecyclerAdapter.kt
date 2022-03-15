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

class MyFriendRecyclerAdapter(
    val mContext : Context,
    val mList : List<UserData>,
)
    : RecyclerView.Adapter<MyFriendRecyclerAdapter.MyViewHolder>(
) {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val imgProfile = view.findViewById<ImageView>(R.id.imgProfile)
        val txtNickname = view.findViewById<TextView>(R.id.txtNickname)
        val imgSocialLoginLogo = view.findViewById<ImageView>(R.id.imgSocialLoginLogo)
        val txtEmail = view.findViewById<TextView>(R.id.txtEmail)

        fun bind(data: UserData) {
            Glide.with(mContext).load(data.profile_img).into(imgProfile)
            txtNickname.text = data.nick_name
            when(data.provider) {
                "default" -> {
                    txtEmail.text = data.email
                    imgSocialLoginLogo.visibility = View.GONE
                }
                "kakao" -> {
                    imgSocialLoginLogo.visibility = View.VISIBLE
                    //imgSocialLoginLog.setImageResource(R.drawable)
                    txtEmail.text = "카카오로그인"
                }
                "fasebook" -> {
                    imgSocialLoginLogo.visibility = View.VISIBLE
                    //imgSocialLoginLog.setImageResource(R.drawable)
                    txtEmail.text = "페북 로그인"
                }
                "naver" -> {
                    imgSocialLoginLogo.visibility = View.VISIBLE
                    //imgSocialLoginLog.setImageResource(R.drawable)
                    txtEmail.text = "네이버로그인"
                }
                else -> {
//                    그 외의 작업
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.my_friend_list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = mList[position]
        holder.bind(data)

    }

    override fun getItemCount() = mList.size
}