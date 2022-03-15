package kr.co.smartsoft.keepthetime_t_20220311.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import kr.co.smartsoft.keepthetime_t_20220311.R
import kr.co.smartsoft.keepthetime_t_20220311.adapters.MyFriendRecyclerAdapter
import kr.co.smartsoft.keepthetime_t_20220311.adapters.RequestUserRecyclerAdapter
import kr.co.smartsoft.keepthetime_t_20220311.databinding.FragmentMyFriendsBinding
import kr.co.smartsoft.keepthetime_t_20220311.databinding.FragmentRequestedUsersBinding
import kr.co.smartsoft.keepthetime_t_20220311.datas.BasicResponse
import kr.co.smartsoft.keepthetime_t_20220311.datas.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestedtUsersFragment : BaseFragment() {
    lateinit var binding: FragmentRequestedUsersBinding

    val mRequestedUsersList = ArrayList<UserData>()

    lateinit var mAdapter: RequestUserRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_requested_users, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupEvents()
        setValues()
    }
    override fun setupEvents() {
    }

    override fun setValues() {

    }

    fun getRequestedUsersFromServer() {
        apiList.getRequestFriendList("requested").enqueue(object :Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful) {
                    mRequestedUsersList.clear()
                    mRequestedUsersList.addAll(response.body()!!.data.friends)
                    mAdapter.notifyDataSetChanged()
                }

            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })

    }
}