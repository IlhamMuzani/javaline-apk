package com.ilham.javaline.ui.status_perjalanan

import com.ilham.javaline.data.model.kendaraan.ResponseKendaraan
import com.ilham.javaline.data.model.kendaraan.ResponseKendaraanDetail
import com.ilham.javaline.data.model.kendaraan.ResponseKendaraanUpdate
import com.ilham.javaline.data.model.pelanggan.ResponsePelanggan
import com.ilham.javaline.data.model.tujuan.ResponseTujuan
import com.ilham.javaline.data.model.user.ResponseUser

interface StatusContract {

    interface Presenter {
        fun getPelanggan()
        fun getTujuan()
        fun getTujuankosong()
        fun getDetail(id: String)
        fun tungguMuat(id: Long, user_id: String, km: String)
        fun loadingMuat(id: Long, user_id: String, pelanggan_id: String)
        fun perjalananIsi(id: Long, user_id: String, km: String, tujuan: String)
        fun tungguBongkar(id: Long, user_id: String, km: String)
        fun loadingBongkar(id: Long, user_id: String)
        fun PerjalananKosong(id: Long, user_id: String, km: String, tujuan: String)
        fun PerbaikanDijalan(id: Long, user_id: String, km: String)
        fun PerbaikanDigarasi(id: Long, user_id: String, km: String)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun showtungguMuat()
        fun showLoadingMuat(responsePelanggan: ResponsePelanggan)
        fun showPerjalananIsi(responseTujuan: ResponseTujuan)
        fun showTungguBongkar()
        fun showLoadingBongkar()
        fun showPerjalananKosong(responseTujuan: ResponseTujuan)
        fun showPerbaikanDijalan()
        fun showPerbaikanDigarasi()
        fun onResultpelanggan(responsePelanggan: ResponsePelanggan)
        fun onResulttujuan(responseTujuan: ResponseTujuan)
        fun onResulttujuankosong(responseTujuan: ResponseTujuan)
        fun onLoading(loading: Boolean,  message: String? = "Loading...")
        fun onResult(responseKendaraanUpdate: ResponseKendaraanUpdate)
        fun onResultDetail(responseKendaraanDetail: ResponseKendaraanDetail)
        fun showSuccessOk(message: String)
        fun showSuccess(message: String)
        fun showError(message: String)
        fun showAlert(phone: String, message: String)
    }
}