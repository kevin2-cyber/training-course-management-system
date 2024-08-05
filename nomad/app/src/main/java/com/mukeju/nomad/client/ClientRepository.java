package com.mukeju.nomad.client;

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

    public LiveData<List<Client>> getClientLiveData() {
        MutableLiveData<List<Client>> data = new MutableLiveData<>();
        Call<List<Client>> response = clientService.getClients();
        // to perform network request in the background thread and
        // handle the response in the main (UI) thread.
        response.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                List<Client> list = response.body();
                data.setValue(list);
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable throwable) {

            }
        });
        return data;
    }
}
