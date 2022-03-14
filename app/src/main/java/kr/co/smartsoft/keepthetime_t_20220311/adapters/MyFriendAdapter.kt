package kr.co.smartsoft.keepthetime_t_20220311.adapters

import android.widget.ArrayAdapter
import kr.co.smartsoft.keepthetime_t_20220311.datas.UserData
import kr.co.smartsoft.keepthetime_t_20220311.utils.ContextUtil

class MyFriendAdapter(
    val mContext: ContextUtil
    resId : Int,
    val mList: List<UserData>
) : ArrayAdapter<UserData>(mContext, resId, mList){
}