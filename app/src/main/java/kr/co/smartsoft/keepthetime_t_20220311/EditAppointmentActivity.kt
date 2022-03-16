package kr.co.smartsoft.keepthetime_t_20220311

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.PathOverlay
import com.odsay.odsayandroidsdk.API
import com.odsay.odsayandroidsdk.ODsayData
import com.odsay.odsayandroidsdk.ODsayService
import com.odsay.odsayandroidsdk.OnResultCallbackListener
import kr.co.smartsoft.keepthetime_t_20220311.databinding.ActivityEditAppointmentBinding
import kr.co.smartsoft.keepthetime_t_20220311.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.nio.file.Path
import java.text.SimpleDateFormat
import java.util.*

class EditAppointmentActivity : BaseActivity() {
    lateinit var binding : ActivityEditAppointmentBinding

    var marker : Marker? = null

    var path: PathOverlay? = null

    var mSeletedLatLng : LatLng? = null

    var mSelectedAppointmentDateTime = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_edit_appointment)
        setUpEvents()
        setValues()
    }

    override fun setUpEvents() {
        binding.btnSave.setOnClickListener {
//            입력값들이 제대로 되어있는지 확인 => 잘못되다면 막아주장
            val inputTitle = binding.edtTitle.text.toString()
//            입력하지 않았다면 거부
//            시간을 선택하지 않았다면 막자
//            기준? txtDate, txtTime 처음 문구 와 동일하면 입력이
            if (inputTitle.isEmpty()) {
                Toast.makeText(mContext, "제목을 입력해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (binding.txtDate.text == "약속 일자") {
                Toast.makeText(mContext, "일자선택요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (binding.txtDate.text == "약속 시간") {
                Toast.makeText(mContext, "시간선택요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            선택한 일시가 현재 이전으로 선택하면
            val now = Calendar.getInstance()
            if (mSelectedAppointmentDateTime.timeInMillis < now.timeInMillis) {
                Toast.makeText(mContext, "현재 이후의 시간으로 선택해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val inputPlaceName = binding.edtPlaceName.text.toString()
            if (inputPlaceName == null) {
                Toast.makeText(mContext, "약소장소를 입력해 주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (mSeletedLatLng == null) {
                Toast.makeText(mContext, "약속 장소를 선택하지 않았습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val lat = mSeletedLatLng!!.latitude
            val lon = mSeletedLatLng!!.longitude
            Log.d("선택한약속장소 - 위도", "위도 : ${lat}")
            Log.d("선택한약속장소 - 위도", "위도 : ${lon}")

            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")
            val sdt = sdf.format(mSelectedAppointmentDateTime.time)

            apiList.postRequestAddAppointment(
                inputTitle,
                sdt,
                inputPlaceName,
                lat,
                lon
            ).enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(mContext, "약속을 등록했습니다.", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })
        }

        binding.txtDate.setOnClickListener {
            val dsl = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    Toast.makeText(mContext, "${year}년 ${month}월 ${dayOfMonth}일 선택함", Toast.LENGTH_SHORT).show()
                    mSelectedAppointmentDateTime.set(year, month, dayOfMonth)
                    val sdf = SimpleDateFormat("yy/MM/dd")
                    val apv = sdf.format(mSelectedAppointmentDateTime.time)

                    binding.txtDate.text = apv
                }
            }
            val dpd = DatePickerDialog(
                mContext,
                dsl,
                mSelectedAppointmentDateTime.get(Calendar.YEAR),
                mSelectedAppointmentDateTime.get(Calendar.MONTH),
                mSelectedAppointmentDateTime.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.txtTime.setOnClickListener {
            val tsl = object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(p0: TimePicker?, hourOfDay: Int, minute: Int) {
                    Toast.makeText(mContext, "${hourOfDay}시 ${minute}분 선택", Toast.LENGTH_SHORT).show()
                    mSelectedAppointmentDateTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    mSelectedAppointmentDateTime.set(Calendar.MINUTE, minute)

                    val sdf = SimpleDateFormat("a h시 m분")
                    binding.txtTime.text = sdf.format(mSelectedAppointmentDateTime.time)
                }
            }
            val tpd = TimePickerDialog(
                mContext,
                tsl,
                18,
                0,
                false
            ).show()
        }
    }

    override fun setValues() {

        binding.naverMapView.getMapAsync {
            val naverMap = it
            val coord = LatLng( 37.577935237988264, 127.03360346916413)
            val cameraUpdate = CameraUpdate.scrollTo(coord)
            naverMap.moveCamera(cameraUpdate)

            naverMap.setOnMapClickListener { pointF, latLng ->
//                 Log.d("크릭된 위/경도", "위도 : ${latLng.latitude}, 경도 : ${latLng.longitude}")
//
                if (marker == null) {
                    marker = Marker()
                }
                marker!!.position = latLng
                marker!!.map = naverMap
//                약속 장소도 새 좌표로 설정ㅅ
                mSeletedLatLng = latLng

                val myODsayService = ODsayService.init(mContext, "gw9m7KtTSb97PJwg3C/jQx+OKMjdKmugQKqgeBp44Vk" )

                myODsayService.requestSearchPubTransPath(
                    coord.longitude.toString(),
                    coord.latitude.toString(),
                    latLng.longitude.toString(),
                    latLng.latitude.toString(),
                    null,
                    null,
                    null,
                    object : OnResultCallbackListener {
                        override fun onSuccess(p0: ODsayData?, p1: API?) {
                            val jsonObj = p0!!.json!!
                            Log.d("길찾기응답", jsonObj.toString())

                            val resultObj = jsonObj.getJSONObject("result")
                            Log.d("result", resultObj.toString())

                            val pathArr = resultObj.getJSONArray("path") // 여러 추천 경로 중 첫번째 만 사용해보자.

                            val firstPathObj = pathArr.getJSONObject(0) // 무조건 0 번째 경로 추출.
                            Log.d("첫번째경로", firstPathObj.toString())

                            val infoObj = firstPathObj.getJSONObject("info")
                            
                            val totalTime = infoObj.getInt("totalTime")
                            val payment = infoObj.getInt("payment")

//                            네이버 지도 라이브러리 이용
                            val infoWindow = InfoWindow()
                            infoWindow.adapter = object : InfoWindow.DefaultTextAdapter(mContext) {
                                override fun getText(p0: InfoWindow): CharSequence {
                                    return "이동시간 : ${totalTime}분, 비용 : ${payment}원"        
                                }

                            }
                            infoWindow.open(marker!!)
                            marker!!.setOnClickListener {
                                if (marker!!.infoWindow == null) {
                                    infoWindow.open(marker!!)
                                }
                                else {
                                    infoWindow.close()
                                }
                                return@setOnClickListener true
                            }

                            val cameraUpdate = CameraUpdate.scrollTo(latLng)
                            naverMap.moveCamera(cameraUpdate)
                        }

                        override fun onError(p0: Int, p1: String?, p2: API?) {
                        }

                    }
                )
                if (path == null) {
                    path = PathOverlay()
                }

                val coordList = ArrayList<LatLng>()

                coordList.add(coord)
                coordList.add(latLng)

                path!!.coords = coordList

                path!!.map = naverMap

            }


        }

    }
}