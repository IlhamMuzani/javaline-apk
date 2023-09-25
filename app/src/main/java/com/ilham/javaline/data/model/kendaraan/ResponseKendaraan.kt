package com.ilham.javaline.data.model.kendaraan

import com.google.gson.annotations.SerializedName

class ResponseKendaraan (
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: List<String>,
    @SerializedName("data") val data: List<DataKendaraan>
)