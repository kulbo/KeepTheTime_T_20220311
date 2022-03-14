package kr.co.smartsoft.keepthetime_t_20220311

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import kr.co.smartsoft.keepthetime_t_20220311.adapters.MainViewPagerAdapter
import kr.co.smartsoft.keepthetime_t_20220311.databinding.ActivityMainBinding
import kr.co.smartsoft.keepthetime_t_20220311.datas.BasicResponse
import kr.co.smartsoft.keepthetime_t_20220311.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {
    lateinit var binding: ActivityMainBinding

    lateinit var mAdapter : MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setUpEvents()
        setValues()
    }

    override fun setUpEvents() {

    }

    override fun setValues() {

//        GET - /user 접근해서 내 정보 조회
//        토큰값이 필요함. =>
        mAdapter = MainViewPagerAdapter(supportFragmentManager)
        binding.mainViewPager.adapter = mAdapter

        binding.mainTabLayout.setupWithViewPager(binding.mainViewPager)
    }
}