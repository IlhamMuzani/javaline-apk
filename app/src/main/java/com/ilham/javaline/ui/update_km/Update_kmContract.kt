package com.ilham.javaline.ui.update_km

import com.ilham.javaline.data.model.kendaraan.ResponseKendaraan

interface Update_kmContract {

    interface Presenter {
        fun updatekm(id:Long, km: String)
        fun getKendaraan()
        fun getDetailkendaraan(id: String)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean, message: String? = "Loading...")
        fun onResult(responseKendaraan: ResponseKendaraan)
        fun onResultlist(responseKendaraan: ResponseKendaraan)
        fun onResultdetail(responseKendaraan: ResponseKendaraan)
        fun showSuccessOk(message: String)
        fun showSuccess(message: String)
        fun showError(message: String)
        fun showAlert(phone: String, message: String)
    }
}