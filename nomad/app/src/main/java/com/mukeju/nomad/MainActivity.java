package com.mukeju.nomad;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mukeju.nomad.client.Client;
import com.mukeju.nomad.client.ClientList;
import com.mukeju.nomad.client.ClientRVAdapter;
import com.mukeju.nomad.client.viewmodel.ClientViewModel;
import com.mukeju.nomad.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Client> clients;
    private RecyclerView recyclerView;
    private ClientRVAdapter clientRVAdapter;
    private ActivityMainBinding mainBinding;
    private ClientViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(ClientViewModel.class);

        getAllClients();
        mainBinding.swipeToRefresh.setColorSchemeResources(R.color.black);
        mainBinding.swipeToRefresh.setOnRefreshListener(this::getAllClients);
    }

    private void getAllClients() {
        viewModel.getClientListLiveData().observe(this, clientLiveData -> {
            clients = clientLiveData;
            displayClientInRV();
        });
    }

    private void displayClientInRV() {
        recyclerView = mainBinding.rvClients;
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        clientRVAdapter = new ClientRVAdapter(this);
        recyclerView.setAdapter(clientRVAdapter);

        clientRVAdapter.setClients((ClientList) clients);

        clientRVAdapter.notifyDataSetChanged();
    }
}