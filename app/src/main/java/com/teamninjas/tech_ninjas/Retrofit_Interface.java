package com.teamninjas.tech_ninjas;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ShivamArora on 29-12-2016.
 */

public interface Retrofit_Interface {


        @GET("getinfo/")
    Call<Response> getResponse(@Query("instance") String instance) ;
}
