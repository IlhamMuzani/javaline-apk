package com.ilham.javaline.ui.fragment.fragment.homedriver

import com.ilham.javaline.data.model.kendaraan.ResponseKendaraan


interface HomedriverContract {

    interface Presenter{
        fun getKendaraan(id: Long)
        fun searchKendaraan(keyword: String)
    }

    interface View{
        fun initFragment(view: android.view.View)
        fun onLoading(loading: Boolean)
        fun onLoadingswet(loading: Boolean, message: String? = "Menampilkan..")
        fun showSuccesOk(message: String)
        fun showSucces(message: String)
        fun onResult(responseKendaraan: ResponseKendaraan)
        fun showError(message: String)
        fun showAlert(message: String)
    }
}