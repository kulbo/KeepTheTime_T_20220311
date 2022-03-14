package kr.co.smartsoft.keepthetime_t_20220311

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.smartsoft.keepthetime_t_20220311.adapters.SearchedUserRecyclerAdapter
import kr.co.smartsoft.keepthetime_t_20220311.databinding.ActivityManageMyFriendsBinding
import kr.co.smartsoft.keepthetime_t_20220311.databinding.ActivitySearchUserBinding
import kr.co.smartsoft.keepthetime_t_20220311.datas.BasicResponse
import kr.co.smartsoft.keepthetime_t_20220311.datas.UserData
import kr.co.smartsoft.keepthetime_t_20220311.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchUserActivity : BaseActivity() {

    lateinit var binding : ActivitySearchUserBinding

    val mSearchedUserList = ArrayList<UserData>()

    lateinit var mAdapter : SearchedUserRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_user)
        setUpEvents()
        setValues()
    }

    override fun setUpEvents() {
        binding.btnSearch.setOnClickListener {
            val inputKeyword = binding.edtNickname.text.toString()

            apiList.getRequestSearchUser(
                ContextUtil.getLoginUserToken(mContext),
                inputKeyword
            ).enqueue(object : Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    mSearchedUserList.clear()

                    if (response.isSuccessful) {
                        val br = response.body()!!

                        mSearchedUserList.addAll(br.data.users)

                        mAdapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })
        }

    }

    override fun setValues() {

        mAdapter = SearchedUserRecyclerAdapter(mContext, mSearchedUserList)
        binding.userRecyclerView.adapter = mAdapter

        binding.userRecyclerView.layoutManager = LinearLayoutManager(mContext)
    }
}