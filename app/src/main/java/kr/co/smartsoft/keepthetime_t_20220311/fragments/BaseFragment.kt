package kr.co.smartsoft.keepthetime_t_20220311.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import kr.co.smartsoft.keepthetime_t_20220311.api.APIList
import kr.co.smartsoft.keepthetime_t_20220311.api.ServerAPI

// 프래그면트의 공통 기능 추가
abstract class BaseFragment : Fragment() {
    lateinit var mContext : Context

    lateinit var apiList : APIList

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mContext = requireContext()

        val retrofit = ServerAPI.getRetrofit()
        apiList = retrofit.create( APIList::class.java)

    }
    abstract fun setupEvents()
    abstract fun setValues()
}