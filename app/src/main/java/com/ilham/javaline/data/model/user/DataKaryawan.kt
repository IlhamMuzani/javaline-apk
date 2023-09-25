package com.ilham.javaline.data.model.user

import com.google.gson.annotations.SerializedName

class DataKaryawan(
    @SerializedName("id") val id: String?,
    @SerializedName("nama_lengkap") val nama_lengkap: String?,
)