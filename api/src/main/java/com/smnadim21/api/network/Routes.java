package com.smnadim21.api.network;


import com.google.gson.JsonObject;
import com.smnadim21.api.model.CheckStatusModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Routes {
    @Headers({"Accept: application/json"})
    @POST("api/reg?pwd=bdapps2019")
    Call<JsonObject> register(@Query("app_id") String app_id, @Query("password") String password);

    //shakiba integration
    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    @POST("api/submit_otp")
    Call<JsonObject> sumitOtpwithDevice(@Field("code") String code, @Field("device_id") String device_id);

    @Headers({"Accept: application/json"})
    @GET("api/checkSubscriptionStatus")
    Call<CheckStatusModel> checkSubStatus(@Query("app_id") String app_id, @Query("device_id") String device_id);

    //bdapps.b-rain.com.au/api/reg?pwd=bdapps2019&app_id=APP_020858&password=0dc33105a73479189855ae41b1cef672


}


