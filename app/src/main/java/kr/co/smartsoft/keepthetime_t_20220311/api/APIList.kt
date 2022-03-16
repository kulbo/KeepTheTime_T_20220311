package kr.co.smartsoft.keepthetime_t_20220311.api

import kr.co.smartsoft.keepthetime_t_20220311.datas.BasicResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface APIList {

//    BASE_URL의 서버에서 어떤 기능들을 사용할 것인지 명시
//    https://keepthetime.xyz/api/docs/ 주소에서 받는 통신 약식을 만들어 주는 Interface

//    로그인
    @FormUrlEncoded     // 파라미터중에 Field(formData)에 담아야하는 파라미터가 있다.
    @POST("/user")
    fun postRequestLogin(
        @Field("email") email: String,
        @Field("password") pw: String
    ) : Call<BasicResponse>     // 서버가 주는 응답을 (성공시에) BasicResponse 형태로 연결

//    회원가입
    @FormUrlEncoded
    @PUT("/user")
    fun putRequestSignUp(
        @Field("email") email: String,
        @Field("password") pw: String,
        @Field("nick_name") nick: String
    ) : Call<BasicResponse>

//    내정보 조회
    @GET("/user")
    fun getRequestMyInfo(
    ) : Call<BasicResponse>

//    중복검사
    @GET("/user/check")
    fun getRequestDuplicatedCheck(
        @Query("type") type : String,
        @Query("value") value : String,
    ) : Call<BasicResponse>

//    내 친구 목록 조회하기
    @GET("/user/friend")
    fun getRequestFriendList(
        @Query("type") type:String
    ) : Call<BasicResponse>

//    사용자 닉네임으로 검색하기
    @GET("/search/user")
    fun getRequestSearchUser(
        @Query("nickname") nickname: String,
    ) : Call<BasicResponse>

    @FormUrlEncoded
    @POST("/user/friend")
    fun postRequestAddFriend(
        @Field("user_id") userId : Int,
    ) : Call<BasicResponse>

    @FormUrlEncoded
    @PUT("/user/friend")
    fun putRequestAceeptOrDenyFriend(
        @Field("user_id") userId : Int,
        @Field("type") type : String,
    ) : Call<BasicResponse>

    @FormUrlEncoded
    @POST("/appointment")
    fun postRequestAddAppointment(
        @Field("title") title : String,
        @Field("date_time") datatime : String,
        @Field("place") place : String,
        @Field("latitude") latitude : Double,
        @Field("longtitude") longtitute : Double,
    ) : Call<BasicResponse>

    //    내 친구 목록 조회하기
    @GET("/user/friend")
    fun getRequestAppointmentList(
        @Query("date") date : String
    ) : Call<BasicResponse>
}