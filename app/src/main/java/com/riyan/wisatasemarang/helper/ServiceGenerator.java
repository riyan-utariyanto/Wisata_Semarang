package com.riyan.wisatasemarang.helper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by riyan on 20/12/2017.
 */

public class ServiceGenerator {
    private static final String BASE_URL = "https://script.google.com/macros/s/AKfycbxgz3U1s6LftcWQHBkKp5yRTP6BmIZ6H17EfTdnwOQZWcbx4ZE/exec/";
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
