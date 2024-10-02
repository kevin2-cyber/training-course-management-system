package com.mukeju.nomad.client;


import retrofit2.Call;
import retrofit2.http.GET;

public interface ClientService {

    @GET("api/v1/clients/")
    Call<ClientList> getClients();
}
