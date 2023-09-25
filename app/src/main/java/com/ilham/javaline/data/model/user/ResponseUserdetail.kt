package com.ilham.javaline.data.model.user

import com.google.gson.annotations.SerializedName

data class ResponseUserdetail(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: List<String>,
    @SerializedName("data") val data: List<DataUser>
)