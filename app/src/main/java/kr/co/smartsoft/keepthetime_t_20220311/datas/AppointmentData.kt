package kr.co.smartsoft.keepthetime_t_20220311.datas

import java.io.Serializable
import java.util.*

class AppointmentData(
    val id : Int,
    val user_id:Int,
    val title:String,
    val datetime:Date,
    val start_place: String,
    val start_latitude:Double,
    val start_longtitude:Double,
    val place : String,
    val latitude : Double,
    val longitude : Double,
    val create_at : String,
    val user: UserData,
    val invited_friends: List<UserData>,
)  : Serializable {
}