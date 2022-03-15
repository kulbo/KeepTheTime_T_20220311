package kr.co.smartsoft.keepthetime_t_20220311.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kr.co.smartsoft.keepthetime_t_20220311.fragments.MyFiendsFragment
import kr.co.smartsoft.keepthetime_t_20220311.fragments.RequestedtUsersFragment

class FriendViewPagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount() = 2

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> MyFiendsFragment()
            else -> RequestedtUsersFragment()
        }
    }

    override fun getPageTitle(position: Int) : CharSequence? {
        return when(position) {
            0 -> "내 친구 목록"
            else -> "친구 요청 목록"
        }
    }
}