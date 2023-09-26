package com.ilham.javaline.data.model.pelanggan

import com.google.gson.annotations.SerializedName

class ResponsePelanggan (
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: List<String>,
    @SerializedName("data") val data: List<DataPelanggan>
)