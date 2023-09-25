package com.ilham.javaline.ui.fragment.fragment.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.ilham.javaline.R
import com.ilham.javaline.data.database.PrefsManager

class NotificationsFragment : Fragment(), NotificationsContract.View {

    lateinit var prefsManager: PrefsManager
    lateinit var presenter: NotificationsPresenter

    lateinit var viewpager : ViewPager
    lateinit var btn_tabs : TabLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notifications, container, false)

        initFragment(view)

        return view
    }

    override fun onStart() {
        super.onStart()
        
    }

    override fun initFragment(view: View) {

    }

    override fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}