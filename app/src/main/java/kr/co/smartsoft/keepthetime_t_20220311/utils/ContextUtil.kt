package kr.co.smartsoft.keepthetime_t_20220311.utils

import android.content.Context

// SharedPreference(Cookie)를 이용해서 데이터 저장 과 가져오기를 하는 모듈
class ContextUtil {

    companion object {

        // 파일 이름
        private val prefName = "KeepTheTimePref"

        // 사용자 토큰 항목명
        private val LOGIN_USER_TOKEN = "LOGIN_USER_TOKEN"

        // token을 저장하는 함수
        fun setLoginUserToken( context:Context, token: String) {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putString(LOGIN_USER_TOKEN, token).apply()
        }

        // token을 가져오는 함수
        fun getLoginUserToken( context: Context) : String {
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getString(LOGIN_USER_TOKEN, "")!!
        }
    }
}