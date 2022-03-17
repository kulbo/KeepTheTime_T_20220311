package kr.co.smartsoft.keepthetime_t_20220311

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import kr.co.smartsoft.keepthetime_t_20220311.databinding.ActivitySignInBinding
import kr.co.smartsoft.keepthetime_t_20220311.databinding.ActivitySignUpBinding
import kr.co.smartsoft.keepthetime_t_20220311.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : BaseActivity() {

    lateinit var binding : ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        setUpEvents()
        setValues()
    }

    override fun setUpEvents() {

        binding.btnEmailCheck.setOnClickListener {
            val inputEmail = binding.edtEmail.text.toString()

           apiList.getRequestDuplicatedCheck("EMAIL",inputEmail).enqueue(object : Callback<BasicResponse> {
               override fun onResponse(
                   call: Call<BasicResponse>,
                   response: Response<BasicResponse>
               ) {
                   if(response.isSuccessful) {
                       Toast.makeText(mContext, "사용해도 좋은 아이디 입니다.", Toast.LENGTH_SHORT).show()
                   } else {
                       Toast.makeText(mContext, "다른 아이디를 사용하세요.", Toast.LENGTH_SHORT).show()
                   }
               }

               override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

               }

           })
        }

        binding.btnSignUp.setOnClickListener {
            val inputEmail = binding.edtEmail.text.toString()
            val inputPw = binding.edtPassword.text.toString()
            val inputNickname = binding.edtNickname.text.toString()

//            회원가입 API 호출(PUT - "/user")
            apiList.putRequestSignUp(inputEmail, inputPw, inputNickname).enqueue(object : Callback<BasicResponse>{
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {

                    if (response.isSuccessful) {
                        val br = response.body()!!

                        Toast.makeText(mContext, "${br.data.user.id}번째 회원입니다.", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })
        }
    }

    override fun setValues() {

    }
}