package com.mukeju.nomad.client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    // Acts as a central configuration point
    // for defining how HTTP requests and responses
    // should be handled.
    String clientUrl = "http://10.11.41.250:4000/api/";

    public Retrofit getRetrofitInstance() {
     return new Retrofit.Builder()
             .baseUrl(clientUrl)
             .addConverterFactory(GsonConverterFactory.create())
             .build();
    }
}
