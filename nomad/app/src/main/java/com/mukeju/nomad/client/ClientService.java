package com.mukeju.nomad.client;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ClientService {

    @GET("v1/clients")
    Call<List<Client>> getClients();
}
