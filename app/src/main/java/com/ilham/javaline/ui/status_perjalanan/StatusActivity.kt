package com.ilham.javaline.ui.status_perjalanan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.ilham.javaline.R
import com.ilham.javaline.data.database.PrefsManager
import com.ilham.javaline.data.model.Constant
import com.ilham.javaline.data.model.kendaraan.DataKendaraan
import com.ilham.javaline.data.model.kendaraan.ResponseKendaraan
import com.ilham.javaline.data.model.kendaraan.ResponseKendaraanDetail
import com.ilham.javaline.data.model.kendaraan.ResponseKendaraanUpdate
import com.ilham.javaline.data.model.user.ResponseUser
import com.ilham.javaline.ui.fragment.UserActivity
import com.ilham.javaline.ui.login.LoginActivity
import com.ilham.javaline.ui.register.RegisterPresenter
import com.ilham.javaline.ui.sweetalert.SweetAlertDialog
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_status.*
import kotlinx.android.synthetic.main.dialog_konfirmasi.view.*
import kotlinx.android.synthetic.main.dialog_loadingbongkar.view.*
import kotlinx.android.synthetic.main.dialog_loadingmuat.view.*
import kotlinx.android.synthetic.main.dialog_perbaikandigarasi.view.*
import kotlinx.android.synthetic.main.dialog_perbaikandijalan.view.*
import kotlinx.android.synthetic.main.dialog_perjalanankosong.view.*
import kotlinx.android.synthetic.main.dialog_tunggubongkar.view.*
import kotlinx.android.synthetic.main.dialog_tunggumuat.view.*
import kotlinx.android.synthetic.main.toolbar.*
import java.text.NumberFormat
import java.util.*

class StatusActivity : AppCompatActivity(), StatusContract.View {

    lateinit var presenter: StatusPresenter
    lateinit var kendaraan: DataKendaraan

    lateinit var prefsManager: PrefsManager

    private lateinit var sLoading: SweetAlertDialog
    private lateinit var sSuccess: SweetAlertDialog
    private lateinit var sError: SweetAlertDialog
    private lateinit var sAlert: SweetAlertDialog

    private var totalSeconds = 0
    private var isTimerRunning = false
    private lateinit var handler: Handler
    private lateinit var timerTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statusdua)
        presenter = StatusPresenter(this)
        prefsManager = PrefsManager(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.getDetail( Constant.KENDARAAN_ID.toString() )
    }

    override fun initActivity() {
//        startTimer()

        tv_nama.text = "Status Perjalanan"

        sLoading = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
        sSuccess = SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE).setTitleText("Berhasil")
        sError = SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE).setTitleText("Gagal!")
        sAlert = SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE).setTitleText("Perhatian!")

//        timerTextView = findViewById(R.id.timertunggu)
//        handler = Handler()
    }

    override fun initListener() {

        ivKembali.setOnClickListener {
            startActivity(Intent(this, UserActivity::class.java))
        }

        tunggu_muat.setOnClickListener {
            showtungguMuat()
        }

        loading_muat.setOnClickListener {
            showLoadingMuat()
        }

        perjalanan_isi.setOnClickListener {
            showPerjalananIsi()
        }

        tunggu_bongkar.setOnClickListener {
            showTungguBongkar()
        }

        loading_bongkar.setOnClickListener {
            showLoadingBongkar()
        }

        perjalanan_kosong.setOnClickListener {
            showPerjalananKosong()
        }

        perbaikan_dijalan.setOnClickListener {
            showPerbaikanDijalan()
        }

        perbaikan_digarasi.setOnClickListener {
            showPerbaikanDigarasi()
        }
    }

    override fun onLoading(loading: Boolean, message: String?) {
        when (loading) {
            true -> {
                sLoading.setContentText(message).show()
            }
            false -> {
                sLoading.dismiss()
            }
        }
    }

    override fun onResult(responseKendaraanUpdate: ResponseKendaraanUpdate) {
        if (responseKendaraanUpdate.status) {
            showSuccessOk(responseKendaraanUpdate.msg)
        } else {
            showError(responseKendaraanUpdate.msg)
        }
    }

    override fun showtungguMuat() {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.dialog_tunggumuat, null)

        view.btn_tolaktunggu.setOnClickListener {
            dialog.dismiss()
        }
        view.btn_konfirmasitunggu.setOnClickListener {
//            val tujuan = view.edt_tujuan.text
            val kmtunggu = view.edt_kmtunggumuat.text
            if (kmtunggu.isNullOrEmpty())
            {
                showError("Masukkan Km")
            } else {
                presenter.tungguMuat(Constant.KENDARAAN_ID, prefsManager.prefsId, kmtunggu.toString())
                dialog.dismiss()
            }
        }
        dialog.setContentView(view)
        dialog.show()
    }

    override fun showLoadingMuat() {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.dialog_loadingmuat, null)

        view.btn_tolakloadingmuat.setOnClickListener {
            dialog.dismiss()
        }

        view.btn_konfirmasiloadingmuat.setOnClickListener {

                presenter.loadingMuat(Constant.KENDARAAN_ID, prefsManager.prefsId)
                dialog.dismiss()
        }
        dialog.setContentView(view)
        dialog.show()
    }

    override fun showPerjalananIsi() {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.dialog_konfirmasi, null)

        view.btn_tolakperjalananisi.setOnClickListener {
            dialog.dismiss()
        }
        view.btn_konfirmasiperjalananisi.setOnClickListener {

            val km = view.edt_kmperjalananisi.text
            if (km.isNullOrEmpty()) {
                showError("Masukkan Km")
            } else {
                presenter.perjalananIsi(Constant.KENDARAAN_ID, prefsManager.prefsId, km.toString())
                dialog.dismiss()
            }
        }
        dialog.setContentView(view)
        dialog.show()
    }

    override fun showTungguBongkar() {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.dialog_tunggubongkar, null)

        view.btn_tolaktunggubongkar.setOnClickListener {
            dialog.dismiss()
        }
        view.btn_konfirmasitunggubongkar.setOnClickListener {

            val km = view.edt_kmtunggu_bongkar.text
            if (km.isNullOrEmpty()) {
                showError("Masukkan Km")
            } else {
                presenter.tungguBongkar(Constant.KENDARAAN_ID, prefsManager.prefsId, km.toString())
                dialog.dismiss()
            }
        }
        dialog.setContentView(view)
        dialog.show()
    }

    override fun showLoadingBongkar() {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.dialog_loadingbongkar, null)


        view.btn_tolakloadingbongkar.setOnClickListener {
            dialog.dismiss()
        }
        view.btn_konfirmasiloadingbongkar.setOnClickListener {

                presenter.loadingBongkar(Constant.KENDARAAN_ID, prefsManager.prefsId)
                dialog.dismiss()
        }
        dialog.setContentView(view)
        dialog.show()
    }

    override fun showPerjalananKosong() {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.dialog_perjalanankosong, null)

        view.btn_tolakperjalanankosong.setOnClickListener {
            dialog.dismiss()
        }
        view.btn_konfirmasiperjalanankosong.setOnClickListener {

            val km = view.edt_kmperjalanankosong.text
            if (km.isNullOrEmpty()) {
                showError("Masukkan Km")
            } else {
                presenter.PerjalananKosong(Constant.KENDARAAN_ID, prefsManager.prefsId, km.toString())
                dialog.dismiss()
            }
        }
        dialog.setContentView(view)
        dialog.show()
    }

    override fun showPerbaikanDijalan() {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.dialog_perbaikandijalan, null)

        view.btn_tolakperbaikandijalan.setOnClickListener {
            dialog.dismiss()
        }
        view.btn_konfirmasiperbaikandijalan.setOnClickListener {

            val km = view.edt_kmperbaikandijalan.text
            if (km.isNullOrEmpty()) {
                showError("Masukkan Km")
            } else {
                presenter.PerbaikanDijalan(Constant.KENDARAAN_ID, prefsManager.prefsId, km.toString())
                dialog.dismiss()
            }
        }
        dialog.setContentView(view)
        dialog.show()
    }

    override fun showPerbaikanDigarasi() {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.dialog_perbaikandigarasi, null)

        view.btn_tolakperbaikandigarasi.setOnClickListener {
            dialog.dismiss()
        }
        view.btn_konfirmasiperbaikandigarasi.setOnClickListener {

            val km = view.edt_kmperbaikandigarasi.text
            if (km.isNullOrEmpty()) {
                showError("Masukkan Km")
            } else {
                presenter.PerbaikanDigarasi(Constant.KENDARAAN_ID, prefsManager.prefsId, km.toString())
                dialog.dismiss()
            }
        }
        dialog.setContentView(view)
        dialog.show()
    }

    override fun onResultDetail(responseKendaraanDetail: ResponseKendaraanDetail) {
        kendaraan = responseKendaraanDetail.kendaraan

        if (kendaraan.status_perjalanan == "Tunggu Muat")
        {
            tunggu_muat.visibility = View.GONE
            tunggu_muathijau.visibility = View.VISIBLE
            loading_muathijau.visibility = View.GONE
            perjalanan_isihijau.visibility = View.GONE
            tunggu_bongkarhijau.visibility = View.GONE
            loading_bongkarhijau.visibility = View.GONE
            perjalanan_kosonghijau.visibility = View.GONE
            perbaikan_dijalanhijau.visibility = View.GONE
            perbaikan_digarasihijau.visibility = View.GONE
            loading_muat.visibility = View.VISIBLE
            perjalanan_isi.visibility = View.VISIBLE
            tunggu_bongkar.visibility = View.VISIBLE
            loading_bongkar.visibility = View.VISIBLE
            perjalanan_kosong.visibility = View.VISIBLE
            perbaikan_dijalan.visibility = View.VISIBLE
            perbaikan_digarasi.visibility = View.VISIBLE
        } else if (kendaraan.status_perjalanan == "Loading Muat")
        {
            loading_muat.visibility = View.GONE
            tunggu_muathijau.visibility = View.GONE
            loading_muathijau.visibility = View.VISIBLE
            perjalanan_isihijau.visibility = View.GONE
            tunggu_bongkarhijau.visibility = View.GONE
            perjalanan_kosonghijau.visibility = View.GONE
            perbaikan_dijalanhijau.visibility = View.GONE
            perbaikan_digarasihijau.visibility = View.GONE
            loading_bongkarhijau.visibility = View.GONE
            tunggu_muat.visibility = View.VISIBLE
            loading_bongkar.visibility = View.VISIBLE
            perjalanan_isi.visibility = View.VISIBLE
            tunggu_bongkar.visibility = View.VISIBLE
            perjalanan_kosong.visibility = View.VISIBLE
            perbaikan_dijalan.visibility = View.VISIBLE
            perbaikan_digarasi.visibility = View.VISIBLE
        } else if (kendaraan.status_perjalanan == "Perjalanan Isi")
        {
            perjalanan_isi.visibility = View.GONE
            tunggu_muathijau.visibility = View.GONE
            loading_muathijau.visibility = View.GONE
            perjalanan_isihijau.visibility = View.VISIBLE
            tunggu_bongkarhijau.visibility = View.GONE
            loading_bongkarhijau.visibility = View.GONE
            perjalanan_kosonghijau.visibility = View.GONE
            perbaikan_dijalanhijau.visibility = View.GONE
            perbaikan_digarasihijau.visibility = View.GONE
            loading_muat.visibility = View.VISIBLE
            tunggu_muat.visibility = View.VISIBLE
            tunggu_bongkar.visibility = View.VISIBLE
            loading_bongkar.visibility = View.VISIBLE
            perjalanan_kosong.visibility = View.VISIBLE
            perbaikan_dijalan.visibility = View.VISIBLE
            perbaikan_digarasi.visibility = View.VISIBLE
        } else if (kendaraan.status_perjalanan == "Tunggu Bongkar")
        {
            tunggu_bongkar.visibility = View.GONE
            tunggu_muathijau.visibility = View.GONE
            loading_muathijau.visibility = View.GONE
            perjalanan_isihijau.visibility = View.GONE
            tunggu_bongkarhijau.visibility = View.VISIBLE
            loading_bongkarhijau.visibility = View.GONE
            perjalanan_kosonghijau.visibility = View.GONE
            perbaikan_dijalanhijau.visibility = View.GONE
            perbaikan_digarasihijau.visibility = View.GONE
            loading_muat.visibility = View.VISIBLE
            perjalanan_isi.visibility = View.VISIBLE
            tunggu_muat.visibility = View.VISIBLE
            loading_bongkar.visibility = View.VISIBLE
            perjalanan_kosong.visibility = View.VISIBLE
            perbaikan_dijalan.visibility = View.VISIBLE
            perbaikan_digarasi.visibility = View.VISIBLE
        } else if (kendaraan.status_perjalanan == "Loading Bongkar")
        {
            tunggu_muathijau.visibility = View.GONE
            loading_muathijau.visibility = View.GONE
            perjalanan_isihijau.visibility = View.GONE
            tunggu_bongkarhijau.visibility = View.GONE
            loading_bongkarhijau.visibility = View.VISIBLE
            perjalanan_kosonghijau.visibility = View.GONE
            perbaikan_dijalanhijau.visibility = View.GONE
            perbaikan_digarasihijau.visibility = View.GONE
            loading_muat.visibility = View.VISIBLE
            perjalanan_isi.visibility = View.VISIBLE
            tunggu_bongkar.visibility = View.VISIBLE
            tunggu_muat.visibility = View.VISIBLE
            perjalanan_kosong.visibility = View.VISIBLE
            perbaikan_dijalan.visibility = View.VISIBLE
            perbaikan_digarasi.visibility = View.VISIBLE
        }else if (kendaraan.status_perjalanan == "Perjalanan Kosong")
        {
            perjalanan_kosong.visibility = View.GONE
            tunggu_muathijau.visibility = View.GONE
            loading_muathijau.visibility = View.GONE
            perjalanan_isihijau.visibility = View.GONE
            tunggu_bongkarhijau.visibility = View.GONE
            loading_bongkarhijau.visibility = View.GONE
            perjalanan_kosonghijau.visibility = View.VISIBLE
            perbaikan_dijalanhijau.visibility = View.GONE
            perbaikan_digarasihijau.visibility = View.GONE
            loading_muat.visibility = View.VISIBLE
            perjalanan_isi.visibility = View.VISIBLE
            tunggu_bongkar.visibility = View.VISIBLE
            loading_bongkar.visibility = View.VISIBLE
            tunggu_muat.visibility = View.VISIBLE
            perbaikan_dijalan.visibility = View.VISIBLE
            perbaikan_digarasi.visibility = View.VISIBLE
        }else if (kendaraan.status_perjalanan == "Perbaikan di jalan")
        {
            perbaikan_dijalan.visibility = View.GONE
            tunggu_muathijau.visibility = View.GONE
            loading_muathijau.visibility = View.GONE
            perjalanan_isihijau.visibility = View.GONE
            tunggu_bongkarhijau.visibility = View.GONE
            loading_bongkarhijau.visibility = View.GONE
            perjalanan_kosonghijau.visibility = View.GONE
            perbaikan_dijalanhijau.visibility = View.VISIBLE
            perbaikan_digarasihijau.visibility = View.GONE
            loading_muat.visibility = View.VISIBLE
            perjalanan_isi.visibility = View.VISIBLE
            tunggu_bongkar.visibility = View.VISIBLE
            loading_bongkar.visibility = View.VISIBLE
            perjalanan_kosong.visibility = View.VISIBLE
            tunggu_muat.visibility = View.VISIBLE
            perbaikan_digarasi.visibility = View.VISIBLE
        }else if (kendaraan.status_perjalanan == "Perbaikan di garasi")
        {
            perbaikan_digarasi.visibility = View.GONE
            tunggu_muathijau.visibility = View.GONE
            loading_muathijau.visibility = View.GONE
            perjalanan_isihijau.visibility = View.GONE
            tunggu_bongkarhijau.visibility = View.GONE
            loading_bongkarhijau.visibility = View.GONE
            perjalanan_kosonghijau.visibility = View.GONE
            perbaikan_dijalanhijau.visibility = View.GONE
            perbaikan_digarasihijau.visibility = View.VISIBLE
            loading_muat.visibility = View.VISIBLE
            perjalanan_isi.visibility = View.VISIBLE
            tunggu_bongkar.visibility = View.VISIBLE
            loading_bongkar.visibility = View.VISIBLE
            perjalanan_kosong.visibility = View.VISIBLE
            perbaikan_dijalan.visibility = View.VISIBLE
            tunggu_muat.visibility = View.VISIBLE
        }
    }

//    private fun startTimer() {
//        isTimerRunning = true
//        handler.post(object : Runnable {
//            override fun run() {
//                if (isTimerRunning) {
//                    totalSeconds++
//                    val hours = totalSeconds / 3600
//                    val minutes = (totalSeconds % 3600) / 60
//                    val seconds = totalSeconds % 60
//                    val timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds)
//                    timerTextView.text = timeString
//                    handler.postDelayed(this, 1000) // Update setiap 1 detik
//                }
//            }
//        })
//    }

    // Method ini dipanggil ketika Anda ingin menghentikan timer
    private fun stopTimer() {
        isTimerRunning = false
    }

    override fun onPause() {
        super.onPause()
        // Hentikan timer saat activity di-pause
        stopTimer()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

//    override fun showSuccessOk(message: String) {
//        sSuccess
//            .setContentText(message)
//            .setConfirmText("OK")
//            .setConfirmClickListener {
//                it.dismissWithAnimation()
////                presenter.getDetail( Constant.KENDARAAN_ID.toString() )
////                finish()
//                startActivity(Intent(this, StatusActivity::class.java))
//            }
//            .show()
//    }

    override fun showSuccessOk(message: String) {
        sSuccess
            .setContentText(message)
            .setConfirmText("OK")
            .setConfirmClickListener {
                it.dismiss()
                presenter.getDetail( Constant.KENDARAAN_ID.toString() )
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