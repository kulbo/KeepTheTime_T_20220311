package kr.co.smartsoft.keepthetime_t_20220311.datas

import java.io.Serializable

class PlaceData(
    val id : Int,
    val user_id : Int,
    val name : String,
    val latitude : Double,
    val logitude : Double,
    val is_primary : Boolean,
) : Serializable {
}