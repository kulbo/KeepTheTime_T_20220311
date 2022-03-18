package kr.co.smartsoft.keepthetime_t_20220311

import android.app.ActionBar
import android.content.Context
import android.os.Bundle
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import kr.co.smartsoft.keepthetime_t_20220311.api.APIList
import kr.co.smartsoft.keepthetime_t_20220311.api.ServerAPI

abstract class BaseActivity : AppCompatActivity() {
    lateinit var mContext : Context

//    모든 화면에서 apiList 변수가 있다면 => aiList 서버기능() 형태로 간단히 코딩 가능
    lateinit var apiList : APIList

//    액션바의 UI요소들을 멤버변수로 => 상속받은 화면들이 각자 컨트롤 가능


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this

        val retrofit = ServerAPI.getRetrofit(mContext)
        apiList = retrofit.create(APIList::class.java)      // apiList의 양식에 retrofit의 통신기능 사용할 수 있도록 연결

        supportActionBar?.let {
            setCustomActionBar()
        }
    }
    abstract fun setUpEvents()
    abstract fun setValues()

    fun setCustomActionBar() {
        val defaultActionBar = supportActionBar!!

        // defaultActionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        defaultActionBar.setDisplayShowCustomEnabled(true)

        defaultActionBar.setCustomView(R.layout.my_custom_action_bar)

        val toolbar = defaultActionBar.customView.parent as androidx.appcompat.widget.Toolbar

        toolbar.setContentInsetsAbsolute(0, 0)
    }
}