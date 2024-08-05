package com.mukeju.nomad.client.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mukeju.nomad.client.Client;
import com.mukeju.nomad.client.ClientRepository;

import java.util.List;

public class ClientViewModel extends ViewModel {
    ClientRepository clientRepository = new ClientRepository();
    LiveData<List<Client>> clientListLiveData;

    public ClientViewModel() {
        this.clientListLiveData = clientRepository.getClientLiveData();
    }

    public LiveData<List<Client>> getClientListLiveData() {
        return clientListLiveData;
    }
}
