package com.ilham.javaline.ui.qr_code

import com.ilham.javaline.data.model.kendaraan.ResponseKendaraan

interface Qr_codeContract {

    interface Presenter {
        fun getKendaraan()
    }

    interface View{

        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResult(responseKendaraan: ResponseKendaraan)
        fun showMessage(message:String)

    }
}