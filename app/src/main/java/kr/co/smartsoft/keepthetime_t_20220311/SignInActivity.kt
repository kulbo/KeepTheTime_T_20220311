package kr.co.smartsoft.keepthetime_t_20220311

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import kr.co.smartsoft.keepthetime_t_20220311.api.APIList
import kr.co.smartsoft.keepthetime_t_20220311.api.ServerAPI
import kr.co.smartsoft.keepthetime_t_20220311.databinding.ActivitySignInBinding
import kr.co.smartsoft.keepthetime_t_20220311.datas.BasicResponse
import kr.co.smartsoft.keepthetime_t_20220311.utils.ContextUtil
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

        binding.btnSignUp.setOnClickListener {
            val myIntent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myIntent)
        }
        binding.btnLogin.setOnClickListener {

            val inputEmail = binding.edtEmail.text.toString()
            val inputPassword = binding.edtPassword.text.toString()

            apiList.postRequestLogin(inputEmail, inputPassword).enqueue(object : Callback<BasicResponse> {
                override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                    Log.d("응답확인", response.toString())

//                    retrobif 라이브러리의 response는 성공/실패 여부에 따라 다른 본문을 봐야함.
//                    성공?
                    if(response.isSuccessful) {
//                        모든 결과가 최종 성공인 경우. response.body() 이용.

                        val br = response.body()!!  //성공시 무조건 본문이 있다. => BasicResponse 형태의 변수로 파싱되어 나옴.

//                        Retrofit의 Callback은 UIThread 안으로 다시 돌아오도록 처리되어있다.
//                        UI 조작을 위해 runOnUiThread { } 작성 필요 X.
                        Toast.makeText(mContext, "${br.data.user.nick_name}님 환영합니다.", Toast.LENGTH_SHORT).show()

//                        서버가 내려주는
                        ContextUtil.setLoginUserToken(mContext, br.data.token)

//                        메인 화면으로 이동, 현재 화면 종료
                        val myIntent = Intent(mContext, MainActivity::class.java)
                        startActivity(myIntent)

                        finish()
                    }
//                    실태일떄 - response.errorBody() 활용
                    else {

                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
//                    서버에 물리적 연결 실패.
                }

            })

        }
    }

    override fun setValues() {

    }
}