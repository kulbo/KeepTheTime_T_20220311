package kr.co.smartsoft.keepthetime_t_20220311

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.overlay.Marker
import kr.co.smartsoft.keepthetime_t_20220311.databinding.ActivityEditMyPlaceBinding

class EditMyPlaceActivity : BaseActivity() {
    lateinit var binding : ActivityEditMyPlaceBinding

    var mSelectedPoint : LatLng? = null

    var marker : Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_my_place)
        setUpEvents()
        setValues()
    }

    override fun setUpEvents() {


    }

    override fun setValues() {
        binding.naverMapView.getMapAsync {
            val naverMap = it
            naverMap.setOnMapClickListener { pointF, latLng ->
                if (mSelectedPoint == null) {
                    marker = Marker()
                }
                mSelectedPoint = latLng

                marker!!.position = latLng
                marker!!.map = naverMap
            }
        }

    }
}