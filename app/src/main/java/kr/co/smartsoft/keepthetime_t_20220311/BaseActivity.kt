package kr.co.smartsoft.keepthetime_t_20220311

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.co.smartsoft.keepthetime_t_20220311.api.APIList
import kr.co.smartsoft.keepthetime_t_20220311.api.ServerAPI

abstract class BaseActivity : AppCompatActivity() {
    lateinit var mContext : Context

//    모든 화면에서 apiList 변수가 있ㄷ면 => aiList 서버기능() 형태로 간단히 코딩 가능
    lateinit var apiList : APIList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this

        val retrofit = ServerAPI.getRetrofit()
        apiList = retrofit.create(APIList::class.java)
    }
    abstract fun setUpEvents()
    abstract fun setValues()

}