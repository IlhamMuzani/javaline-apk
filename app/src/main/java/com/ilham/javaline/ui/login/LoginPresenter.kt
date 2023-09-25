package com.ilham.javaline.ui.login
import com.ilham.javaline.network.ApiService
import com.ilham.javaline.data.database.PrefsManager
import com.ilham.javaline.data.model.user.DataUser
import com.ilham.javaline.data.model.user.ResponseUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter (val view: LoginContract.View) : LoginContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
        view.onLoading(false)
    }
    override fun doLogin(kode_user: String, password: String) {
        view.onLoading(true)
        ApiService.endpoint.login(kode_user, password).enqueue(object : Callback<ResponseUser>{
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                view.onLoading(false)
                if (response.isSuccessful){
                    val responseUser: ResponseUser? = response.body()
                    view.onResult(responseUser!!)
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                view.onLoading(false)
            }
        })
    }

    override fun setPrefs(prefsManager: PrefsManager, dataUser: DataUser) {
        prefsManager.prefIsLogin = true
        prefsManager.prefsId = dataUser.id!!
        prefsManager.prefs_is_kode_user = dataUser.kode_user!!
//        prefsManager.prefs_is_role = dataUser.role!!
    }
}