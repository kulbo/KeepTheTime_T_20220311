package kr.co.smartsoft.keepthetime_t_20220311.datas

class AppointmentData(
    val id : Int,
    val user_id:Int,
    val title:String,
    val datatime:String,
    val start_place: String,
    val start_latitude:Double,
    val start_longtitude:Double,
    val place : String,
    val latitude : Double,
    val longitude : Double,
    val create_at : String,
    val user: UserData,
    val invited_friends: List<UserData>
) {
}