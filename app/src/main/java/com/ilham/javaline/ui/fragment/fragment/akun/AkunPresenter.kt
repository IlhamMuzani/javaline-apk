package com.ilham.javaline.ui.fragment.fragment.akun

import com.ilham.javaline.network.ApiService
import com.ilham.javaline.data.database.PrefsManager
import com.ilham.javaline.data.model.user.ResponseUserdetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AkunPresenter (val view: AkunContract.View): AkunContract.Presenter {

    override fun doLogin(prefsManager: PrefsManager) {
        if (prefsManager.prefIsLogin) view.onResultLogin(prefsManager)
    }

    override fun doLogout(prefsManager: PrefsManager) {
        prefsManager.logout()
        view.showSuccessOk("Berhasil Keluar")
        view.onResultLogout()
    }

    override fun profildetail(id: String) {
        ApiService.endpoint.driver_detail(id).enqueue(object :
            Callback<ResponseUserdetail> {
            override fun onResponse(call: Call<ResponseUserdetail>, response: Response<ResponseUserdetail>) {
                if (response.isSuccessful){
                    val responseUserdetail: ResponseUserdetail? = response.body()
                    if (responseUserdetail!!.status)
                        view.onResult(responseUserdetail!!)
                }
            }

            override fun onFailure(call: Call<ResponseUserdetail>, t: Throwable) {
            }

        })
    }
}