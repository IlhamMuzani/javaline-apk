package com.ilham.javaline.ui.fragment.fragment.homedriver

import com.ilham.javaline.data.model.kendaraan.ResponseKendaraan
import com.ilham.javaline.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomedriverPresenter (var view: HomedriverContract.View) : HomedriverContract.Presenter {

    override fun getKendaraan(id: Long) {
        view.onLoading(true)
        view.onLoadingswet(true, "Loading..")
        ApiService.endpoint.getkendaraan(id).enqueue(object : Callback<ResponseKendaraan> {
            override fun onResponse(
                call: Call<ResponseKendaraan>,
                response: Response<ResponseKendaraan>
            ) {
                view.onLoadingswet(false)
                view.onLoading(false)
                if (response.isSuccessful) {
                    val responseProdukList: ResponseKendaraan? = response.body()
                    view.onResult( responseProdukList!! )
                }
            }

            override fun onFailure(call: Call<ResponseKendaraan>, t: Throwable) {
                view.onLoadingswet(false)
                view.onLoading(false)
            }

        })
    }
    override fun searchKendaraan(keyword: String) {
        view.onLoading(true)
        view.onLoadingswet(true, "Loading..")
        ApiService.endpoint.Searchkendaraan(keyword).enqueue( object : Callback<ResponseKendaraan>{
            override fun onResponse(
                call: Call<ResponseKendaraan>,
                response: Response<ResponseKendaraan>
            ) {
                view.onLoadingswet(false, "Loading..")
                view.onLoading(false)
                if (response.isSuccessful){
                    val responseKendaraan: ResponseKendaraan? = response.body()
                    view.onResult(responseKendaraan!!)
                }
            }

            override fun onFailure(call: Call<ResponseKendaraan>, t: Throwable) {
                view.onLoadingswet(false, "Loading..")
                view.onLoading(false)
            }

        })
    }

}