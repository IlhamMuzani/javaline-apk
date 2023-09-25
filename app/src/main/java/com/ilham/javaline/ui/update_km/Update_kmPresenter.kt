package com.ilham.javaline.ui.update_km

import com.ilham.javaline.network.ApiService
import com.ilham.javaline.data.model.kendaraan.ResponseKendaraan
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Update_kmPresenter (val view: Update_kmContract.View) : Update_kmContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
        view.onLoading(false)
    }

    override fun updatekm(
        id: Long,
        km: String,
    ) {
        view.onLoading(true, "Loading ...")
        ApiService.endpoint.updatekm(
            id,
            km,

        ).enqueue(object : Callback<ResponseKendaraan> {
            override fun onResponse(
                call: Call<ResponseKendaraan>,
                response: Response<ResponseKendaraan>
            ) {
                view.onLoading(false)
                if (response.isSuccessful) {
                    val responseKendaraan: ResponseKendaraan? = response.body()
                    view.onResult(responseKendaraan!!)
                }
            }

            override fun onFailure(call: Call<ResponseKendaraan>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }

    override fun getKendaraan() {
//        view.onLoading(true, "loading...")
//        ApiService.endpoint.getkendaraan().enqueue(object : Callback<ResponseKendaraan>{
//            override fun onResponse(call: Call<ResponseKendaraan>, response: Response<ResponseKendaraan>) {
//                view.onLoading(false)
//                if (response.isSuccessful){
//                    val responseKendaraan: ResponseKendaraan? = response.body()
//                    view.onResultlist(responseKendaraan!!)
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseKendaraan>, t: Throwable) {
//                view.onLoading(false)
//            }
//
//        })
    }

    override fun getDetailkendaraan(id: String) {
        view.onLoading(true, "loading...")
        ApiService.endpoint.getDetail(id).enqueue(object : Callback<ResponseKendaraan>{
            override fun onResponse(call: Call<ResponseKendaraan>, response: Response<ResponseKendaraan>) {
                view.onLoading(false)
                if (response.isSuccessful){
                    val responseKendaraan: ResponseKendaraan? = response.body()
                    view.onResultdetail(responseKendaraan!!)
                }
            }

            override fun onFailure(call: Call<ResponseKendaraan>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }
}