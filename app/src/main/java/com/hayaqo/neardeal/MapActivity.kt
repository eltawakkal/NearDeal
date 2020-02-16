package com.hayaqo.neardeal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.hayaqo.neardeal.model.Store
import com.hayaqo.neardeal.network.ApiClient
import com.hayaqo.neardeal.network.ApiEndPoint
import kotlinx.android.synthetic.main.activity_map.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    lateinit var mGoogleMap: GoogleMap
    lateinit var apiEndPoint: ApiEndPoint
    lateinit var listStore: MutableList<Store>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        map_main.onCreate(null)
        map_main.onResume()
        map_main.getMapAsync(this)

    }

    fun getStoreFromServer() {
        apiEndPoint = ApiClient().getRetrofit().create(ApiEndPoint::class.java)
        val callStore: Call<MutableList<Store>> = apiEndPoint.getStores()
        callStore.enqueue(object : Callback<MutableList<Store>> {
            override fun onFailure(call: Call<MutableList<Store>>, t: Throwable) {
                Log.d("errGetStoreMaps", t.message)
            }

            override fun onResponse(
                call: Call<MutableList<Store>>,
                response: Response<MutableList<Store>>
            ) {
                listStore = response.body()!!

                for (store: Store in listStore) {
                    val location = LatLng(store.lat.toDouble(), store.lng.toDouble())
                    setMarker(location, store.name)
                }
            }

        })
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mGoogleMap = googleMap!!

        getStoreFromServer()
    }

    fun setMarker(location: LatLng, title: String) {
//        val cameraCenter = LatLng(-6.2952849, 106.7921816)
        val cameraCenter = getCenterCamera()
        mGoogleMap.addMarker(MarkerOptions().position(location).title(title))
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cameraCenter, 10f))
    }

    fun getCenterCamera() : LatLng {

        var maxLat = 0.0
        var minLat = 0.0
        var maxLng = 0.0
        var minLng = 0.0

        for (i in 0..listStore.size-1) {
            for (j in 1..listStore.size -1) {
                if (listStore.get(i).lat.toDouble() > listStore.get(j).lat.toDouble()) {
                    maxLat = listStore.get(j).lat.toDouble()
                }
                if (listStore.get(i).lat.toDouble() < listStore.get(j).lat.toDouble()) {
                    minLat = listStore.get(j).lat.toDouble()
                }

                if (listStore.get(i).lng.toDouble() > listStore.get(j).lng.toDouble()) {
                    maxLng = listStore.get(j).lng.toDouble()
                }
                if (listStore.get(i).lng.toDouble() < listStore.get(j).lng.toDouble()) {
                    minLng = listStore.get(j).lng.toDouble()
                }
            }
        }

        val lat = (minLat+maxLat) / 2
        val lng = (minLng+maxLng) / 2

        Log.d("hasilShorting", "Lat: $lat Lng: $lng")

        return LatLng(lat, lng)
    }

}
