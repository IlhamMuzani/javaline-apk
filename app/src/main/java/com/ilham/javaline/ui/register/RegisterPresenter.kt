package com.ilham.javaline.ui.register

import com.ilham.javaline.network.ApiService
import com.ilham.javaline.data.model.user.ResponseUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterPresenter (val view: RegisterContract.View) : RegisterContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
        view.onLoading(false)
    }

    override fun insertregister(
        kode_user: String,
        password: String,
        password_confirmation: String
    ) {
        view.onLoading(true,"Loading..")
        ApiService.endpoint.register(kode_user, password, password_confirmation).enqueue(object:
            Callback<ResponseUser> {
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                view.onLoading(false)
                if (response.isSuccessful) {
                    val responseUser: ResponseUser? = response.body()
                    view.onResult(responseUser!!)
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                view.onLoading(false)
            }

        })
    }

}