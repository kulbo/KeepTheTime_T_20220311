package kr.co.smartsoft.keepthetime_t_20220311

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import kr.co.smartsoft.keepthetime_t_20220311.databinding.ActivityManageMyFriendsBinding

class SearchUserActivity : BaseActivity() {

    lateinit var binding : ActivityManageMyFriendsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_user)
        setUpEvents()
        setValues()
    }

    override fun setUpEvents() {

    }

    override fun setValues() {

    }
}