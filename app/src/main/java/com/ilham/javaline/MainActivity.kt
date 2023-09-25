package com.ilham.javaline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.ilham.javaline.data.database.PrefsManager
import com.ilham.javaline.ui.fragment.UserActivity
import com.ilham.javaline.ui.login.LoginActivity
import com.ilham.javaline.ui.update_km.Update_kmActivity

class MainActivity : AppCompatActivity() {

    lateinit var prefsManager: PrefsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prefsManager = PrefsManager(this)

            if (prefsManager.prefIsLogin){
                Handler().postDelayed({
                    startActivity(Intent(this, UserActivity::class.java))
                    finish()
                }, 2000)
            }else{
                Handler().postDelayed({
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }, 2000)
            }
    }
}