package com.ilham.javaline.ui.update_km

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.ilham.javaline.R
import com.ilham.javaline.data.database.PrefsManager
import com.ilham.javaline.data.model.Constant
import com.ilham.javaline.data.model.kendaraan.DataKendaraan
import com.ilham.javaline.data.model.kendaraan.ResponseKendaraan
import com.ilham.javaline.ui.fragment.UserActivity
import com.ilham.javaline.ui.sweetalert.SweetAlertDialog
import kotlinx.android.synthetic.main.activity_update_km.*
import kotlinx.android.synthetic.main.toolbar.*
import java.text.SimpleDateFormat

class Update_kmActivity : AppCompatActivity(), Update_kmContract.View {


    lateinit var presenter: Update_kmPresenter
    lateinit var prefsManager: PrefsManager
    lateinit var kendaraan: DataKendaraan

    lateinit var calendar: java.util.Calendar
    lateinit var simpleDateFormat: SimpleDateFormat
    lateinit var date: String

    private lateinit var sLoading: SweetAlertDialog
    private lateinit var sSuccess: SweetAlertDialog
    private lateinit var sError: SweetAlertDialog
    private lateinit var sAlert: SweetAlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_km)
        presenter = Update_kmPresenter(this)
        prefsManager = PrefsManager(this)
    }

    override fun onStart() {
        super.onStart()

//        if (kendaraan != null) {
        presenter.getKendaraan()
//        }else {
//        }
    }

    override fun initActivity() {

        sLoading = SweetAlertDialog(
            this,
            SweetAlertDialog.PROGRESS_TYPE
        )
        sSuccess = SweetAlertDialog(
            this,
            SweetAlertDialog.SUCCESS_TYPE
        ).setTitleText("Berhasil")
        sError = SweetAlertDialog(
            this,
            SweetAlertDialog.ERROR_TYPE
        ).setTitleText("Gagal")
        sAlert = SweetAlertDialog(
            this,
            SweetAlertDialog.WARNING_TYPE
        ).setTitleText("Perhatian!")

        tanggalotomatis()

    }

    override fun initListener() {

        ivKembali.setOnClickListener {
            onBackPressed()
        }

        btn_update.setOnClickListener {

            if (Constant.Kendar_id == 0) {
                showError("Pilih Kendaraan !")
            } else if (edt_kmakhir.text!!.isEmpty()) {
                showError("Masukkan Nilai Km !")
            } else

                presenter.updatekm(
                    Constant.Kendar_id.toLong(),
                    edt_kmakhir.text.toString(),
                )
        }
    }

    override fun onLoading(loading: Boolean, message: String?) {
        when (loading) {
            true -> sLoading.setTitleText(message).show()
            false -> sLoading.dismiss()
        }
    }

    override fun onResult(responseKendaraan: ResponseKendaraan) {
        if (responseKendaraan.status) {
//            responseUser.user
            showSuccessOk(responseKendaraan.message[0])
        }else{
            showError(responseKendaraan.message[0])
        }
    }

    override fun onResultlist(responseKendaraan: ResponseKendaraan) {
        spinnerKendaraan(responseKendaraan)
    }

    override fun onResultdetail(responseKendaraan: ResponseKendaraan) {
        val kendaraan = responseKendaraan.data[0]
        edt_km.setText( kendaraan.km )

    }

    private fun tanggalotomatis(){
        calendar = java.util.Calendar.getInstance()
        simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        date = simpleDateFormat.format(calendar.time)
    }

    fun spinnerKendaraan(responseKendaraan: ResponseKendaraan) {

        val arrayString = java.util.ArrayList<String>()
        arrayString.add("Pilih Kendaraan")
        for (kendaraan in responseKendaraan.data) {
            arrayString.add(kendaraan.no_kabin!!)
        }

        val adapter = ArrayAdapter(this, R.layout.item_spinner, arrayString.toTypedArray())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        edtkendaraan.adapter = adapter
        val selection = adapter.getPosition(Constant.Kendar_name)
        edtkendaraan.setSelection(selection)
        edtkendaraan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> {
                        Constant.Kendar_id = 0
                        Constant.Kendar_name = "Pilih Kendaraan"
                    }
                    else -> {
                            val mapel = responseKendaraan.data[position - 1].no_kabin
                            Constant.Kendar_id = responseKendaraan.data[position - 1].id!!.toInt()
                            Constant.Kendar_name = mapel.toString()
                            presenter.getDetailkendaraan(Constant.Kendar_id.toString())
                    }

                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

    }

    override fun showSuccessOk(message: String) {
        sSuccess
            .setContentText(message)
            .setConfirmText("OK")
            .setConfirmClickListener {
                it.dismissWithAnimation()
                finish()
            }
            .show()
    }

    override fun showSuccess(message: String) {
        sSuccess
            .setContentText(message)
            .setConfirmText("OK")
            .setConfirmClickListener {
                it.dismissWithAnimation()
//                val intent = Intent(applicationContext, otpactivity::class.java)
//                intent.putExtra("phone", "+62$phone")
//                intent.putExtra("verificationId", storedVerificationId)
//                startActivity(intent)
            }
            .show()
    }

    override fun showError(message: String) {
        sError
            .setContentText(message)
            .setConfirmText("OK")
            .setConfirmClickListener {
                it.dismiss()
            }
            .show()
    }

    override fun showAlert(phone: String, message: String) {
        sAlert
            .setContentText(message)
            .setConfirmText("Ya")
            .setConfirmClickListener {
                it.dismissWithAnimation()
//                startPhoneNumberVerification(phone)
            }
            .setCancelText("Nanti")
            .setCancelClickListener {
                it.dismiss()
            }
            .show()
        sAlert.setCancelable(true)
    }

}