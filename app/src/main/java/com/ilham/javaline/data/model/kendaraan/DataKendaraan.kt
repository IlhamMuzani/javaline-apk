package com.ilham.javaline.data.model.kendaraan

import com.google.gson.annotations.SerializedName

class DataKendaraan (
    @SerializedName( "id") val id: Long?,
    @SerializedName( "user_id") val user_id: String?,
    @SerializedName( "kode_kendaraan") val kode_kendaraan: String?,
    @SerializedName( "no_kabin") val no_kabin: String?,
    @SerializedName("no_pol") val no_pol: String?,
    @SerializedName("no_rangka") val no_rangka: String?,
    @SerializedName("no_mesin") val no_mesin: String?,
    @SerializedName("keterangan") val keterangan: String?,
    @SerializedName("expired_kir") val expired_kir: String?,
    @SerializedName("expired_stnk") val expired_stnk: String?,
    @SerializedName("status_perjalanan") val status_perjalanan: String?,
    @SerializedName("jenis_kendaraan_id") val jenis_kendaraan: String?,
    @SerializedName("golongan_id") val golongan_id: String?,
    @SerializedName("km") val km: String?,
    @SerializedName("tujuan") val tujuan: String?,
)