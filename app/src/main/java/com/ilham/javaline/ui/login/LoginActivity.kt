package com.ilham.javaline.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ilham.javaline.R
import com.ilham.javaline.data.database.PrefsManager
import com.ilham.javaline.data.model.user.DataUser
import com.ilham.javaline.data.model.user.ResponseUser
import com.ilham.javaline.ui.fragment.UserActivity
import com.ilham.javaline.ui.register.RegisterActivity
import com.ilham.javaline.ui.sweetalert.SweetAlertDialog
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View {

    lateinit var presenter: LoginPresenter
    lateinit var prefsManager: PrefsManager

    private lateinit var sLoading: SweetAlertDialog
    private lateinit var sSuccess: SweetAlertDialog
    private lateinit var sError: SweetAlertDialog
    private lateinit var sAlert: SweetAlertDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logindua)
        presenter = LoginPresenter(this)
        prefsManager = PrefsManager(this)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun initActivity() {
        sLoading = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
        sSuccess = SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE).setTitleText("Berhasil")
        sError = SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE).setTitleText("Gagal!")
        sAlert = SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE).setTitleText("Perhatian!")

    }

    override fun initListener() {

        btnLogin.setOnClickListener {
            if (edit_code.text!!.isEmpty()) {
                showError("Masukan Kode !!")
            } else if (edit_password.text!!.isEmpty()) {
                showError("Masukan Password !!")
            } else
                presenter.doLogin(edit_code.text.toString(), edit_password.text.toString())
        }

//        text_viewRegister.setOnClickListener {
//            startActivity(Intent(this, RegisterActivity::class.java))
//        }

        tv_login.setOnClickListener {
            dummy()
        }
    }

    override fun onLoading(loading: Boolean, message: String?) {
        when (loading) {
            true -> sLoading.setTitleText(message).show()
            false -> sLoading.dismiss()
        }
    }

    override fun onResult(responseUser: ResponseUser) {
        val status: Boolean = responseUser.status
        val message: String = responseUser.message[0]

        if (status){
            val user: DataUser = responseUser.data[0]

            presenter.setPrefs(prefsManager, user)
            showSuccessLogin(message)
        }else{
            if (status == false){
                showError(message)
            }
        }
    }

    override fun showSuccessLogin(message: String) {
        sSuccess
            .setContentText(message)
            .setConfirmText("OK")
            .setConfirmClickListener {
                it.dismissWithAnimation()
                finish()
                startActivity(Intent(this, UserActivity::class.java))
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

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    fun dummy() {
        edit_code.setText("AB000001")
        edit_password.setText("admin")
    }
}