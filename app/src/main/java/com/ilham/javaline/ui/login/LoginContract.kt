package com.ilham.javaline.ui.login

import com.ilham.javaline.data.database.PrefsManager
import com.ilham.javaline.data.model.user.DataUser
import com.ilham.javaline.data.model.user.ResponseUser

interface LoginContract {

    interface Presenter {
        fun doLogin(kode_user:String, password:String)
        fun setPrefs(prefsManager: PrefsManager, dataUser: DataUser)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean, message: String? = "Loading...")
        fun onResult(responseUser: ResponseUser)
        fun showSuccessLogin(message: String)
        fun showSuccess(message: String)
        fun showError(message: String)
        fun showAlert(phone: String, message: String)
    }
}