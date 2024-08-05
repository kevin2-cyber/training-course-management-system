package com.mukeju.nomad.model.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mukeju.nomad.model.ClientList;
import com.mukeju.nomad.model.service.ClientService;
import com.mukeju.nomad.model.service.RetrofitInstance;

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
        response.enqueue(new Callback<ClientList>() {
            @Override
            public void onResponse(Call<ClientList> call, Response<ClientList> response) {
                ClientList list = response.body();
                data.setValue(list);
            }

            @Override
            public void onFailure(Call<ClientList> call, Throwable throwable) {

            }
        });
        return data;
    }
}
