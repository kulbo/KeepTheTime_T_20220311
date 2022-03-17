package kr.co.smartsoft.keepthetime_t_20220311

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import kr.co.smartsoft.keepthetime_t_20220311.adapters.MyPlacesRecyclerAdapter
import kr.co.smartsoft.keepthetime_t_20220311.databinding.ActivityManagePlacesBinding
import kr.co.smartsoft.keepthetime_t_20220311.datas.BasicResponse
import kr.co.smartsoft.keepthetime_t_20220311.datas.PlaceData
import kr.co.smartsoft.keepthetime_t_20220311.datas.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManagePlacesActivity : BaseActivity() {

    lateinit var binding: ActivityManagePlacesBinding

    val mPlaceList  = ArrayList<PlaceData>()

    lateinit var mPlaceAdapter : MyPlacesRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_manage_places)
        setUpEvents()
        setValues()
    }

    override fun setUpEvents() {

    }

    override fun setValues() {

        mPlaceAdapter = MyPlacesRecyclerAdapter(mContext, mPlaceList)
    }

    override fun onResume() {
        super.onResume()
        getMyPlacesFromServer()
    }

    fun getMyPlacesFromServer() {
        apiList.getRequestMyPlaces().enqueue(object:Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful) {
                    val br = response.body()!!

                }
                else {

                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })
    }
}