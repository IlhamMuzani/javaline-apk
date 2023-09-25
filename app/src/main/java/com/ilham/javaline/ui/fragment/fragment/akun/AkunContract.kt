package com.ilham.javaline.ui.fragment.fragment.akun

import com.ilham.javaline.data.database.PrefsManager
import com.ilham.javaline.data.model.user.ResponseUserdetail

interface AkunContract {

    interface Presenter {
        fun doLogin(prefsManager: PrefsManager)
        fun doLogout(prefsManager: PrefsManager)

        fun profildetail(id:String)
    }

    interface View {
        fun initFragment(view: android.view.View)
        fun onResultLogin(prefsManager: PrefsManager)
        fun onResultLogout()
        fun onResult(responseUserdetail: ResponseUserdetail)
        fun showSuccessOk(message: String)
        fun showSuccess(message: String)
        fun showError(message: String)
        fun showAlert(phone: String, message: String)
    }

}