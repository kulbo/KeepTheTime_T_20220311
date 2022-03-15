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
import com.naver.maps.map.overlay.Marker
import kr.co.smartsoft.keepthetime_t_20220311.databinding.ActivityEditAppointmentBinding
import kr.co.smartsoft.keepthetime_t_20220311.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class EditAppointmentActivity : BaseActivity() {
    lateinit var binding : ActivityEditAppointmentBinding

    var marker : Marker? = null

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
            if (mSeletedLatLng == null) {
                Toast.makeText(mContext, "약속 장소를 선택하지 않았습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Log.d("선택한약속장고 - 위도", "위도 : ${mSeletedLatLng!!.latitude}")
            Log.d("선택한약속장고 - 위도", "위도 : ${mSeletedLatLng!!.longitude}")

            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")

            apiList.postRequestAddAppointment(
                binding.edtTitle.text.toString(),
                sdf.format(mSelectedAppointmentDateTime.time),
                binding.edtPlaceName.text.toString(),
                mSeletedLatLng!!.latitude,
                mSeletedLatLng!!.longitude
            ).enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {

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

            marker = Marker()
            marker!!.position = coord
            marker!!.map = naverMap

            mSeletedLatLng = coord
            
            naverMap.setOnMapClickListener { pointF, latLng ->
                Log.d("크릭된 위/경도", "위도 : ${latLng.latitude}, 경도 : ${latLng.longitude}")
                marker!!.position = latLng
                marker!!.map = naverMap
//                약속 장소도 새 좌표로 설정ㅅ
                mSeletedLatLng = latLng
            }
            

        }

    }
}