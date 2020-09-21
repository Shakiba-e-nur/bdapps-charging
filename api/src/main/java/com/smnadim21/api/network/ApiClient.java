package com.smnadim21.api.network;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "http://bdapps.b-rain.com.au/";

    public static Routes getGsonInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Routes.class);
    }

    public static Routes getStringInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
               // .addConverterFactory(ScalarsConverterFactory.create())
                 .addConverterFactory(GsonConverterFactory.create())
                .build().create(Routes.class);
    }


    public static String getBaseUrl() {
        return BASE_URL;
    }
}