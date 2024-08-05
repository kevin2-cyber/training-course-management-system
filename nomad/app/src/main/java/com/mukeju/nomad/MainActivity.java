package com.mukeju.nomad;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mukeju.nomad.client.Client;
import com.mukeju.nomad.client.viewmodel.ClientViewModel;
import com.mukeju.nomad.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Client> clients;
    private RecyclerView recyclerView;
    private ClientRVAdapter clientRVAdapter;
    private ActivityMainBinding mainBinding;
    private ClientViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(ClientViewModel.class);

        getAllClients();
    }

    private void getAllClients() {
        viewModel.getClientListLiveData().observe(this, clientLiveData -> {
            clients = (ArrayList<Client>) clientLiveData;
            displayClientInRV();
        });
    }

    private void displayClientInRV() {
        recyclerView = mainBinding.rvClients;
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        clientRVAdapter = new ClientRVAdapter(this);
        recyclerView.setAdapter(clientRVAdapter);

        clientRVAdapter.setClients(clients);

        clientRVAdapter.notifyDataSetChanged();
    }
}