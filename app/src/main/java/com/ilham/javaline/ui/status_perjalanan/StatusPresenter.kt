package com.ilham.javaline.ui.status_perjalanan

import com.ilham.javaline.data.model.kendaraan.ResponseKendaraan
import com.ilham.javaline.data.model.kendaraan.ResponseKendaraanDetail
import com.ilham.javaline.data.model.kendaraan.ResponseKendaraanUpdate
import com.ilham.javaline.network.ApiService
import com.ilham.javaline.data.model.user.ResponseUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StatusPresenter (val view: StatusContract.View) : StatusContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
        view.onLoading(false)
    }

    override fun getDetail(id: String) {
        view.onLoading(true, "Loading..")
        ApiService.endpoint.getDetailkendaraan(id).enqueue(object: Callback<ResponseKendaraanDetail>{
            override fun onResponse(
                call: Call<ResponseKendaraanDetail>,
                response: Response<ResponseKendaraanDetail>
            ) {
                view.onLoading(false)
                if (response.isSuccessful) {
                    val responseKendaraanDetail: ResponseKendaraanDetail? = response.body()
                    view.onResultDetail(responseKendaraanDetail!!)
                }
            }

            override fun onFailure(call: Call<ResponseKendaraanDetail>, t: Throwable) {
                view.onLoading(false)
            }
        })
    }

    override fun tungguMuat(id: Long, user_id: String, km: String) {
        view.onLoading(true,"Loading..")
        ApiService.endpoint.tunggu_muat(id, user_id, km).enqueue(object : Callback<ResponseKendaraanUpdate>{
            override fun onResponse(
                call: Call<ResponseKendaraanUpdate>,
                response: Response<ResponseKendaraanUpdate>
            ) {
                view.onLoading(false)
                if (response.isSuccessful) {
                    val responseKendaraanUpdate: ResponseKendaraanUpdate? = response.body()
                    view.onResult(responseKendaraanUpdate!!)
                }
            }
            override fun onFailure(call: Call<ResponseKendaraanUpdate>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }

    override fun loadingMuat(id: Long, user_id: String) {
        view.onLoading(true, "Loading..")
        ApiService.endpoint.loading_muat(id, user_id).enqueue(object:Callback<ResponseKendaraanUpdate>{
            override fun onResponse(
                call: Call<ResponseKendaraanUpdate>,
                response: Response<ResponseKendaraanUpdate>
            ) {
                view.onLoading(false)
                if (response.isSuccessful) {
                    val responseKendaraanUpdate: ResponseKendaraanUpdate? = response.body()
                    view.onResult(responseKendaraanUpdate!!)
                }
            }
            override fun onFailure(call: Call<ResponseKendaraanUpdate>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }

    override fun perjalananIsi(id: Long, user_id: String, km: String) {
        view.onLoading(true, "Loading..")
        ApiService.endpoint.perjalanan_isi(id, user_id, km).enqueue(object:Callback<ResponseKendaraanUpdate>{
            override fun onResponse(
                call: Call<ResponseKendaraanUpdate>,
                response: Response<ResponseKendaraanUpdate>
            ) {
                view.onLoading(false)
                if (response.isSuccessful) {
                    val responseKendaraanUpdate: ResponseKendaraanUpdate? = response.body()
                    view.onResult(responseKendaraanUpdate!!)
                }
            }
            override fun onFailure(call: Call<ResponseKendaraanUpdate>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }

    override fun tungguBongkar(id: Long, user_id: String, km: String) {
        view.onLoading(true, "Loading..")
        ApiService.endpoint.tunggu_bongkar(id, user_id, km).enqueue(object:Callback<ResponseKendaraanUpdate>{
            override fun onResponse(
                call: Call<ResponseKendaraanUpdate>,
                response: Response<ResponseKendaraanUpdate>
            ) {
                view.onLoading(false)
                if (response.isSuccessful) {
                    val responseKendaraanUpdate: ResponseKendaraanUpdate? = response.body()
                    view.onResult(responseKendaraanUpdate!!)
                }
            }
            override fun onFailure(call: Call<ResponseKendaraanUpdate>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }

    override fun loadingBongkar(id: Long, user_id: String) {
        view.onLoading(true, "Loading..")
        ApiService.endpoint.loading_bongkar(id, user_id).enqueue(object:Callback<ResponseKendaraanUpdate>{
            override fun onResponse(
                call: Call<ResponseKendaraanUpdate>,
                response: Response<ResponseKendaraanUpdate>
            ) {
                view.onLoading(false)
                if (response.isSuccessful) {
                    val responseKendaraanUpdate: ResponseKendaraanUpdate? = response.body()
                    view.onResult(responseKendaraanUpdate!!)
                }
            }
            override fun onFailure(call: Call<ResponseKendaraanUpdate>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }

    override fun PerjalananKosong(id: Long, user_id: String, km: String) {
        view.onLoading(true, "Loading..")
        ApiService.endpoint.perjalanan_kosong(id, user_id, km).enqueue(object:Callback<ResponseKendaraanUpdate>{
            override fun onResponse(
                call: Call<ResponseKendaraanUpdate>,
                response: Response<ResponseKendaraanUpdate>
            ) {
                view.onLoading(false)
                if (response.isSuccessful) {
                    val responseKendaraanUpdate: ResponseKendaraanUpdate? = response.body()
                    view.onResult(responseKendaraanUpdate!!)
                }
            }
            override fun onFailure(call: Call<ResponseKendaraanUpdate>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }

    override fun PerbaikanDijalan(id: Long, user_id: String,km: String) {
        view.onLoading(true, "Loading..")
        ApiService.endpoint.perbaikan_dijalan(id, user_id, km).enqueue(object:Callback<ResponseKendaraanUpdate>{
            override fun onResponse(
                call: Call<ResponseKendaraanUpdate>,
                response: Response<ResponseKendaraanUpdate>
            ) {
                view.onLoading(false)
                if (response.isSuccessful) {
                    val responseKendaraanUpdate: ResponseKendaraanUpdate? = response.body()
                    view.onResult(responseKendaraanUpdate!!)
                }
            }
            override fun onFailure(call: Call<ResponseKendaraanUpdate>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }

    override fun PerbaikanDigarasi(id: Long, user_id: String, km: String) {
        view.onLoading(true, "Loading..")
        ApiService.endpoint.perbaikan_digarasi(id, user_id, km).enqueue(object:Callback<ResponseKendaraanUpdate>{
            override fun onResponse(
                call: Call<ResponseKendaraanUpdate>,
                response: Response<ResponseKendaraanUpdate>
            ) {
                view.onLoading(false)
                if (response.isSuccessful) {
                    val responseKendaraanUpdate: ResponseKendaraanUpdate? = response.body()
                    view.onResult(responseKendaraanUpdate!!)
                }
            }
            override fun onFailure(call: Call<ResponseKendaraanUpdate>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }
}