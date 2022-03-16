package kr.co.smartsoft.keepthetime_t_20220311.datas

import java.io.Serializable
import java.util.*

class AppointmentData(
    val id : Int,
    val user_id:Int,
    val title:String,
    val datetime:Date,      // Date format으로 해주어야지 SimpleDateFormat 으로 변환시 down 되지 않는다.
    val start_place: String,
    val start_latitude:Double,
    val start_longitude:Double,
    val place : String,
    val latitude : Double,
    val longitude : Double,
    val create_at : String,
    val user: UserData,
    val invited_friends: List<UserData>
)  : Serializable {
}