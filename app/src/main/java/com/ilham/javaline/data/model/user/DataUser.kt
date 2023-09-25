package com.ilham.javaline.data.model.user

import com.google.gson.annotations.SerializedName

class DataUser(
    @SerializedName("id") val id: String?,
    @SerializedName("kode_user") val kode_user: String?,
    @SerializedName("role") val role: String?,
    @SerializedName("karyawan") val karyawan: DataKaryawan
)