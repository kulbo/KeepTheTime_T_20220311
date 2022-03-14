package kr.co.smartsoft.keepthetime_t_20220311

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import kr.co.smartsoft.keepthetime_t_20220311.datas.BasicResponse
import kr.co.smartsoft.keepthetime_t_20220311.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setUpEvents()
        setValues()
    }

    override fun setUpEvents() {

    }

    override fun setValues() {

        var isMyInfoLoaded = false

        apiList.getRequestMyInfo(ContextUtil.getLoginUserToken(mContext)).enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful) {

                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })
//      임시 - 2.5초 후에 무조건 로그인
        val myHandler = Handler(Looper.getMainLooper())

        myHandler.postDelayed( {
            val myIntent : Intent

            if(isMyInfoLoaded) {
                myIntent = Intent(mContext, MainActivity::class.java)
            } else{
                myIntent = Intent(mContext, SignInActivity::class.java)
            }
            startActivity(myIntent)

        }, 2500)


    }
}