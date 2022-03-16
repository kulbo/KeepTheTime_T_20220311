package kr.co.smartsoft.keepthetime_t_20220311.datas

import java.io.Serializable

// 서버가 알려주는 사용자 정보를 담기 위한(파싱하기 위한)클래스

class UserData(
    val id: Int,
    val provider: String,
    val uid: String?,
    val email: String,
    val ready_minute: Int,
    val nick_name: String,
    val profile_img: String
) : Serializable {
}