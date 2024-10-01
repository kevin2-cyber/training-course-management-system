package com.mukeju.nomad.client;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ClientRepository {
    // used to abstract the data source details and
    // provide a clean API for the ViewModel to
    // fetch and manage the data.
    private final ClientService clientService;

    public ClientRepository() {
        this.clientService = new RetrofitInstance()
                .getRetrofitInstance()
                .create(ClientService.class);
    }

    public LiveData<ClientList> getClientLiveData() {
        MutableLiveData<ClientList> data = new MutableLiveData<>();
        Call<ClientList> response = clientService.getClients();
        // to perform network request in the background thread and
        // handle the response in the main (UI) thread.
        response.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<ClientList> call, @NonNull Response<ClientList> response) {
                ClientList list = response.body();
                data.setValue(list);
            }

            @Override
            public void onFailure(@NonNull Call<ClientList> call, @NonNull Throwable throwable) {

            }
        });
        return data;
    }
}
