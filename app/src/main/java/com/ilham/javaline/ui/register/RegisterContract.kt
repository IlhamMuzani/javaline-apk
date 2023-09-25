package com.ilham.javaline.ui.register

import com.ilham.javaline.data.model.user.ResponseUser

interface RegisterContract {

    interface Presenter {
        fun insertregister(kode_user: String, password: String, password_confirmation:String)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean,  message: String? = "Loading...")
        fun onResult(responseUser: ResponseUser)
        fun showSuccessOk(message: String)
        fun showSuccess(message: String)
        fun showError(message: String)
        fun showAlert(phone: String, message: String)
    }
}