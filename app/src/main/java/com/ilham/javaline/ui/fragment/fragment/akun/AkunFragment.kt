package com.ilham.javaline.ui.fragment.fragment.akun

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.ilham.javaline.R
import com.ilham.javaline.data.database.PrefsManager
import com.ilham.javaline.data.model.Constant
import com.ilham.javaline.data.model.user.ResponseUserdetail
import com.ilham.javaline.ui.fragment.UserActivity
import com.ilham.javaline.ui.login.LoginActivity
import com.ilham.javaline.ui.sweetalert.SweetAlertDialog

class AkunFragment : Fragment(), AkunContract.View {

    lateinit var prefsManager: PrefsManager
    lateinit var presenter: AkunPresenter
    lateinit var txvNama: TextView
    lateinit var logout : LinearLayout


    lateinit var sLoading: SweetAlertDialog
    lateinit var sAlert: SweetAlertDialog
    lateinit var sError: SweetAlertDialog
    lateinit var sSuccess: SweetAlertDialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_akun, container, false)

        initFragment(view)
        prefsManager = PrefsManager(requireActivity())
        presenter = AkunPresenter(this)
        presenter.doLogin(prefsManager)

        return view
    }

    override fun onStart() {
        super.onStart()
        presenter.profildetail(prefsManager.prefsId)

    }

    override fun initFragment(view: View) {
        sLoading = SweetAlertDialog(requireActivity(), SweetAlertDialog.PROGRESS_TYPE)
        sSuccess = SweetAlertDialog(requireActivity(), SweetAlertDialog.SUCCESS_TYPE).setTitleText("Berhasil")
        sError = SweetAlertDialog(requireActivity(), SweetAlertDialog.ERROR_TYPE).setTitleText("Gagal")
        sAlert = SweetAlertDialog(requireActivity(), SweetAlertDialog.WARNING_TYPE).setTitleText("Perhatian!")

        txvNama = view.findViewById(R.id.txv_nama)

        logout = view.findViewById(R.id.linelogout)

        logout.setOnClickListener {
            presenter.doLogout(prefsManager)
        }

//        ubahprofil.setOnClickListener {
//            startActivity(Intent(requireActivity(), UpdateprofilActivity::class.java))
//        }

//        ubahpassword.setOnClickListener {
//            Constant.USER_ID = prefsManager.prefsId.toLong()
//            startActivity(Intent(requireActivity(), KatasandibaruActivity::class.java))
//        }


//        ubahpasswordpendidik.setOnClickListener {
//            Constant.USER_ID = prefsManager.prefsId.toLong()
//            startActivity(Intent(requireActivity(), KatasandibarupenActivity::class.java))
//        }

    }

    override fun onResultLogin(prefsManager: PrefsManager) {
    }

    override fun onResultLogout() {
        showSuccessOk("Berhasil Logout")
    }

    override fun onResult(responseUserdetail: ResponseUserdetail) {
        responseUserdetail.data[0]

        val akun = responseUserdetail.data[0]
        txvNama.setText(akun!!.karyawan.nama_lengkap)
    }

    override fun showSuccessOk(message: String) {
        sSuccess
            .setContentText(message)
            .setConfirmText("OK")
            .setConfirmClickListener {
                it.dismissWithAnimation()
                startActivity(Intent(requireActivity(), LoginActivity::class.java))
            }
            .show()
    }


    override fun showSuccess(message: String) {
        sSuccess
            .setContentText(message)
            .setConfirmText("OK")
            .setConfirmClickListener {
                it.dismissWithAnimation()
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