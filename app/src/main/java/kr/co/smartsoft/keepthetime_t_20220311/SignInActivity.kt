package kr.co.smartsoft.keepthetime_t_20220311

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import kr.co.smartsoft.keepthetime_t_20220311.api.APIList
import kr.co.smartsoft.keepthetime_t_20220311.api.ServerAPI
import kr.co.smartsoft.keepthetime_t_20220311.databinding.ActivitySignInBinding
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : BaseActivity() {
    lateinit var binding : ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        setUpEvents()
        setValues()
    }

    override fun setUpEvents() {

        binding.btnLogin.setOnClickListener {

            val inputEmail = binding.edtEmail.text.toString()
            val inputPassword = binding.edtPassword.text.toString()

            apiList.postRequestLogin(inputEmail, inputPassword).enqueue(object : Callback<JSONObject> {
                override fun onResponse(call: Call<JSONObject>, response: Response<JSONObject>) {

                    Log.d("응답확인", response.toString())

//                    retrobif 라이브러리의 response는 성공/실패 여부에 따라 다른 본문을 봐야함.
//                    성공?
                    if(response.isSuccessful) {

                    }
//                    실태일떄 - response.errorBody() 활용
                    else {

                    }
                }

                override fun onFailure(call: Call<JSONObject>, t: Throwable) {
//                    서버에 물리적 연결 실패.
                }

            })

        }
    }

    override fun setValues() {

    }
}