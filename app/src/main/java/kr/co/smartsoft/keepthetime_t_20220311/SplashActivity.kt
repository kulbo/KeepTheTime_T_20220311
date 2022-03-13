package kr.co.smartsoft.keepthetime_t_20220311

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun setUpEvents() {

    }

    override fun setValues() {

//      임시 - 2.5초 후에 무조건 로그인
        val myHandler = Handler(Looper.getMainLooper())

        myHandler.postDelayed( {
            val myIntent = Intent(mContext, SignInActivity::class.java)
            startActivity(myIntent)

            finish()
        }, 2500)


    }
}