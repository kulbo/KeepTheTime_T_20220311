package kr.co.smartsoft.keepthetime_t_20220311.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.co.smartsoft.keepthetime_t_20220311.R
import kr.co.smartsoft.keepthetime_t_20220311.api.APIList
import kr.co.smartsoft.keepthetime_t_20220311.api.ServerAPI
import kr.co.smartsoft.keepthetime_t_20220311.datas.BasicResponse
import kr.co.smartsoft.keepthetime_t_20220311.datas.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestUserRecyclerAdapter(
    val mContext : Context,
    val mList : List<UserData>,
)   : RecyclerView.Adapter<RequestUserRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val imgProfile = view.findViewById<ImageView>(R.id.imgProfile)
        val txtNickname = view.findViewById<TextView>(R.id.txtNickname)
        val imgSocialLoginLogo = view.findViewById<ImageView>(R.id.imgSocialLoginLogo)
        val txtEmail = view.findViewById<TextView>(R.id.txtEmail)
        val btnAccept = view.findViewById<Button>(R.id.btnAccept)
        val btnDeny = view.findViewById<Button>(R.id.btnDeny)

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


//            두개의 버튼이 눌리면 할 일을 하나의 변수에 담아두자.
//            할 일 : Interface => 정석 : object : 인터페이스 종류 { }
//            축약문법(lambda) => 인터페이스종류 {  }

            val ocl = View.OnClickListener {

//                서버에 수락 / 거절 의사 전달.
//                수락버튼 : 수락 / 거절
//                it 변수 : 클릭된 버튼을 담고 있는 역할.
                val tagStr = it.tag.toString()

                Log.d("보낼파라미터", tagStr)

                val retrofit = ServerAPI.getRetrofit(mContext)
                val apiList = retrofit.create(APIList::class.java)

                apiList.putRequestAceeptOrDenyFriend(
                    data.id,
                    tagStr
                ).enqueue(object : Callback<BasicResponse>{
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {

                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    }

                })

            }


            // 요청자가 없을 때 앱이 종료되어 임시로 막음
//            btnAccept.setOnClickListener(ocl)
//            btnDeny.setOnClickListener(ocl)

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