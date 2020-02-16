package com.hayaqo.neardeal.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.hayaqo.neardeal.R
import com.hayaqo.neardeal.adapter.RecStoreAdapter
import com.hayaqo.neardeal.model.Store
import com.hayaqo.neardeal.network.ApiClient
import com.hayaqo.neardeal.network.ApiEndPoint
import kotlinx.android.synthetic.main.fragment_frag_stores.*
import kotlinx.android.synthetic.main.fragment_frag_stores.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragStores : Fragment() {

    lateinit var apiEndPoint: ApiEndPoint

//    companion object {
//        var nama: String = "akil"
//        var umut: Int = 3
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_frag_stores, container, false)

        getDataStoreFromServer(view)

        return view
    }

    fun getDataStoreFromServer(view: View) {
        var listStore = mutableListOf<Store>()

        apiEndPoint = ApiClient().getRetrofit().create(ApiEndPoint::class.java)

        val getStore = apiEndPoint.getStores()
        getStore.enqueue(object : Callback<MutableList<Store>> {
            override fun onFailure(call: Call<MutableList<Store>>, t: Throwable) {
                Log.d("errGetStore", t.message)
            }

            override fun onResponse(call: Call<MutableList<Store>>, response: Response<MutableList<Store>>) {
                Log.d("successGetStore", "${response.body()?.size}" )
                listStore = response.body()!!
                setRecItems(view, listStore)
            }

        })
    }

    //ngeset item dari list ke dalam recylerview
    fun setRecItems(view: View, listStore: MutableList<Store>) {
        val adapter = RecStoreAdapter(context, listStore)
        view.rec_list_store.layoutManager = LinearLayoutManager(context)
        view.rec_list_store.adapter = adapter
    }

}
