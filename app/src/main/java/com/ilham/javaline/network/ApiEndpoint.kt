package com.ilham.javaline.network

import com.ilham.javaline.data.model.kendaraan.ResponseKendaraan
import com.ilham.javaline.data.model.kendaraan.ResponseKendaraanDetail
import com.ilham.javaline.data.model.kendaraan.ResponseKendaraanUpdate
import com.ilham.javaline.data.model.user.ResponseUser
import com.ilham.javaline.data.model.user.ResponseUserdetail
import retrofit2.Call
import retrofit2.http.*

interface ApiEndpoint {

    @FormUrlEncoded
    @POST("driver/register")
    fun register(
        @Field("kode_user") kode_user: String,
        @Field("password") password: String,
        @Field("password_confirmation") password_confirmation: String,
//        @Field("fcm") fcm: String
    ): Call<ResponseUser>

    @FormUrlEncoded
    @POST("driver/login")
    fun login(
        @Field("kode_user") kode_user: String,
        @Field("password") password: String
    ): Call<ResponseUser>

    @GET("driver-detail/{id}")
    fun driver_detail(
        @Path("id") id: String
    ): Call<ResponseUserdetail>

    @GET("list-kendaraan/{id}")
    fun getkendaraan(
        @Path("id") id: Long,
    ): Call<ResponseKendaraan>

    @POST("kendaraan-search")
    fun Searchkendaraan(
        @Query("keyword") keyword: String,
    ): Call<ResponseKendaraan>


    @POST("kendaraan-update/{id}")
    fun updatekm(
        @Path("id") id: Long,
        @Query("km") km: String,
    ): Call<ResponseKendaraan>

    @GET("kendaraan-detail/{id}")
    fun getDetail(
        @Path("id") id: String
    ): Call<ResponseKendaraan>

    @GET("kendaraan-detailken/{id}")
    fun getDetailkendaraan(
        @Path("id") id: String
    ): Call<ResponseKendaraanDetail>

    @POST("kendaraan-tunggumuat/{id}")
    fun tunggu_muat(
        @Path("id") id: Long,
        @Query("user_id") user_id: String,
        @Query("km") km: String,
        ): Call<ResponseKendaraanUpdate>

    @POST("kendaraan-loadingmuat/{id}")
    fun loading_muat(
        @Path("id") id: Long,
        @Query("user_id") user_id: String,
    ): Call<ResponseKendaraanUpdate>

    @POST("kendaraan-perjalananisi/{id}")
    fun perjalanan_isi(
        @Path("id") id: Long,
        @Query("user_id") user_id: String,
        @Query("km") km: String,
        ): Call<ResponseKendaraanUpdate>

    @POST("kendaraan-tunggubongkar/{id}")
    fun tunggu_bongkar(
        @Path("id") id: Long,
        @Query("user_id") user_id: String,
        @Query("km") km: String,
        ): Call<ResponseKendaraanUpdate>

    @POST("kendaraan-loadingbongkar/{id}")
    fun loading_bongkar(
        @Path("id") id: Long,
        @Query("user_id") user_id: String,
    ): Call<ResponseKendaraanUpdate>

    @POST("kendaraan-perjalanankosong/{id}")
    fun perjalanan_kosong(
        @Path("id") id: Long,
        @Query("user_id") user_id: String,
        @Query("km") km: String,
        ): Call<ResponseKendaraanUpdate>

    @POST("kendaraan-perbaikandijalan/{id}")
    fun perbaikan_dijalan(
        @Path("id") id: Long,
        @Query("user_id") user_id: String,
        @Query("km") km: String,
        ): Call<ResponseKendaraanUpdate>

    @POST("kendaraan-perbaikandigarasi/{id}")
    fun perbaikan_digarasi(
        @Path("id") id: Long,
        @Query("user_id") user_id: String,
        @Query("km") km: String,
    ): Call<ResponseKendaraanUpdate>
}