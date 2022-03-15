package kr.co.smartsoft.keepthetime_t_20220311

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import kr.co.smartsoft.keepthetime_t_20220311.adapters.MyFriendAdapter
import kr.co.smartsoft.keepthetime_t_20220311.databinding.ActivityManageMyFriendsBinding
import kr.co.smartsoft.keepthetime_t_20220311.datas.BasicResponse
import kr.co.smartsoft.keepthetime_t_20220311.datas.UserData
import kr.co.smartsoft.keepthetime_t_20220311.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManageMyFriendsActivity : BaseActivity() {
    lateinit var binding : ActivityManageMyFriendsBinding

    lateinit var mAdapter : MyFriendAdapter

//    val mFriendList = ArrayList<UserData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_manage_my_friends)
        setUpEvents()
        setValues()
    }

    override fun setUpEvents() {

        binding.btnAddFriend.setOnClickListener {
            val myIntent = Intent(mContext, SearchUserActivity::class.java)
            startActivity(myIntent)
        }

    }

    override fun setValues() {
//        getMyFriendListFromServer()

//        mAdapter = MyFriendAdapter(mContext, R.layout.activity_manage_my_friends, mFriendList)
//        binding.myFriendsListView.adapter = mAdapter


    }

    fun getMyFriendListFromServer() {
        apiList.getRequestFriendList(
            "my"
        ).enqueue(object :Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful) {
                    val br = response.body()!!

//                    mFriendList.addAll(br.data.friends)

                    mAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })
    }
}