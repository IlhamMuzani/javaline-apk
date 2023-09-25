package com.ilham.javaline.ui.fragment.fragment.homedriver

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ilham.javaline.R
import com.ilham.javaline.data.database.PrefsManager
import com.ilham.javaline.data.model.Constant
import com.ilham.javaline.data.model.kendaraan.DataKendaraan
import com.ilham.javaline.data.model.kendaraan.ResponseKendaraan
import com.ilham.javaline.ui.fragment.UserActivity
import com.ilham.javaline.ui.sweetalert.SweetAlertDialog
import com.ilham.javaline.ui.update_km.Update_kmActivity
import java.text.SimpleDateFormat
import java.util.*

class HomedriverFragment : Fragment(), HomedriverContract.View {

    lateinit var prefsManager: PrefsManager
    lateinit var presenter: HomedriverPresenter
    lateinit var kendaraan: DataKendaraan
    lateinit var kendaraanAdapter: KendaraandriverAdapter
//    lateinit var updateKm : RelativeLayout
    lateinit var gambarKosong : LinearLayout

    lateinit var RcvKendaraan : RecyclerView
    lateinit var Swipe : SwipeRefreshLayout
//    lateinit var EditSearch : EditText

    private lateinit var sLoading: SweetAlertDialog
    private lateinit var sSuccess: SweetAlertDialog
    private lateinit var sError: SweetAlertDialog
    private lateinit var sAlert: SweetAlertDialog

    lateinit var calendar: Calendar
    lateinit var simpleDateFormat: SimpleDateFormat
    lateinit var date: String
    lateinit var tanggal: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_homedriverdua, container, false)

        presenter = HomedriverPresenter(this)
        prefsManager = PrefsManager(requireActivity())

        initFragment(view)

        return view
    }

    override fun onStart() {
        super.onStart()
        presenter.getKendaraan(prefsManager.prefsId.toLong())
        tanggal.text = date

    }

    override fun initFragment(view: View) {

        sLoading = SweetAlertDialog(requireActivity(), SweetAlertDialog.PROGRESS_TYPE)
        sSuccess = SweetAlertDialog(
            requireActivity(),
            SweetAlertDialog.SUCCESS_TYPE
        ).setTitleText("Berhasil")
        sError =
            SweetAlertDialog(requireActivity(), SweetAlertDialog.ERROR_TYPE).setTitleText("Gagal !")
        sAlert = SweetAlertDialog(
            requireActivity(),
            SweetAlertDialog.WARNING_TYPE
        ).setTitleText("Peringatan !")


        calendar = Calendar.getInstance()
        simpleDateFormat = SimpleDateFormat("EEEE, dd-MM-yyyy", Locale("id", "ID"))
        date = simpleDateFormat.format(calendar.time)
        tanggal = view.findViewById(R.id.tanggal)


        RcvKendaraan = view.findViewById(R.id.rcvKendaraan)
        Swipe = view.findViewById(R.id.swipe)
//        EditSearch = view.findViewById(R.id.edtSearch)
        gambarKosong = view.findViewById(R.id.layoutdatatidakada)

        kendaraanAdapter = KendaraandriverAdapter(requireActivity(), arrayListOf()){
                dataKendaraan: DataKendaraan, position: Int, type: String ->
            Constant.KENDARAAN_ID = dataKendaraan.id!!

            kendaraan = dataKendaraan
        }

        RcvKendaraan.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = kendaraanAdapter
        }

        Swipe.setOnRefreshListener {
            presenter.getKendaraan(prefsManager.prefsId.toLong())
        }
//
//        EditSearch.setOnEditorActionListener { v, actionId, event ->
//            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//
//                Constant.KEYWORD = EditSearch.text.toString()
//                presenter.searchKendaraan(Constant.KEYWORD)
//                true
//            } else {
//                false
//            }
//        }
    }

        override fun onResult(responseKendaraan: ResponseKendaraan) {
            val dataKendaraan: List<DataKendaraan> = responseKendaraan.data
            if (responseKendaraan.status) {
                kendaraanAdapter.setData(dataKendaraan)
                gambarKosong.visibility = View.GONE
            } else {
//                showError(responseKendaraan.message.toString())
                gambarKosong.visibility = View.VISIBLE
                RcvKendaraan.visibility = View.GONE
            }
        }

    override fun onLoading(loading: Boolean) {
        when (loading) {
            true -> Swipe.isRefreshing = true
            false -> Swipe.isRefreshing = false
        }
    }

    override fun onLoadingswet(loading: Boolean, message: String?) {
        when (loading){
            true -> sLoading.setTitleText(message).show()
            false -> sLoading.dismiss()
        }
    }

    override fun showSuccesOk(message: String) {
        sSuccess
            .setContentText(message)
            .setConfirmText("Ok")
            .setConfirmClickListener {
                it.dismissWithAnimation()
                startActivity(Intent(requireActivity(), UserActivity::class.java))
            }.show()
    }

    override fun showSucces(message: String) {
        sSuccess
            .setContentText(message)
            .setConfirmText("Ok")
            .setConfirmClickListener {
                it.dismissWithAnimation()
            }.show()
    }


    override fun showError(message: String) {
        sError
            .setContentText(message)
            .setConfirmText("Gagal")
            .setConfirmClickListener {
                it.dismiss()
            }.show()
    }

    override fun showAlert(message: String) {
        sAlert
            .setContentText(message)
            .setConfirmText("Ya")
            .setConfirmClickListener {
                it.dismissWithAnimation()
            }
            .setConfirmText("Nanti")
            .setConfirmClickListener {
                it.dismiss()
            }.show()
        sAlert.setCancelable(true)
    }

}