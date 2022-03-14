package kr.co.smartsoft.keepthetime_t_20220311.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kr.co.smartsoft.keepthetime_t_20220311.R
import kr.co.smartsoft.keepthetime_t_20220311.datas.UserData

class MyFriendAdapter(
    val mContext: Context,
    resId : Int,
    val mList: List<UserData>
) : ArrayAdapter<UserData>(mContext, resId, mList){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if (tempRow == null) {
            tempRow = LayoutInflater.from(mContext).inflate(R.layout.my_friend_list_item, null)
        }
        val row = tempRow!!

        val data = mList[position]

        val imgProfile = row.findViewById<ImageView>(R.id.imgProfile)
        val txtNickname = row.findViewById<TextView>(R.id.txtNickname)
        val txtEmail = row.findViewById<TextView>(R.id.txtEmail)

        Glide.with(mContext).load(data.profile_img).into(imgProfile)
        txtNickname.text = data.nick_name


//        사용자의 provider
//        txtEmail
        when (data.provider) {
            "default" -> {
//                이메일 표시
//                로고 이미지 숨김
                txtEmail.text = data.email

            }
            "kakao" -> {
                txtEmail.text = "카카오로그인"
            }
            "facebook" -> {
                txtEmail.text = "페북 로그인"
            }
            "naver" -> {
                txtEmail.text = "네이버 로그인"
            }
            else -> {

            }
        }

        return row
    }
}