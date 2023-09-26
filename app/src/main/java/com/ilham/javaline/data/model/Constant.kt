package com.ilham.javaline.data.model

class Constant {
    companion object {

        var IP: String = "http://192.168.2.138/javaline/"
//        var IP: String = "http://192.168.1.212/javaline/"
//        var IP: String = "https://javaline.id/"
        var IP_IMAGE: String = IP + "public/storage/uploads/"

        const val LOCATION_PERMISSION_REQUEST_CODE = 1;

        var KENDARAAN_ID: Long = 0
        var KEYWORD: String = ""

        var Kendar_id: Int = 0
        var Kendar_name: String = ""

        var Pelanggan_id: Int = 0
        var Pelanggan_name: String = ""

        var UPDATE: Boolean = false
    }
}