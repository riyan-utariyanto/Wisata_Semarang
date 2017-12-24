package com.riyan.wisatasemarang.helper;

import com.riyan.wisatasemarang.model.ListWisata;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by riyan on 20/12/2017.
 */

public interface ServiceClient {
    @GET("exec")
    Call<ListWisata> getWisata(@Query("sheet") String namaSheet);
}