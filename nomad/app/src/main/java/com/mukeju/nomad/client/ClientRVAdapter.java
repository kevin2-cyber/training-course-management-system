package com.mukeju.nomad.client;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mukeju.nomad.R;
import com.mukeju.nomad.databinding.ClientItemBinding;

import java.util.ArrayList;

public class ClientRVAdapter extends RecyclerView.Adapter<ClientRVAdapter.ClientVH> {
    private final Context context;
    private ClientList clientArrayList;

    public ClientRVAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ClientVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ClientItemBinding clientItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.client_item,
                parent,
                false
        );
        return new ClientVH(clientItemBinding);
    }

    public void setClients(ClientList newClients) {
        final DiffUtil.DiffResult result =
                DiffUtil.calculateDiff(new ClientDiffCallback(clientArrayList, newClients), false);
        clientArrayList = newClients;
        result.dispatchUpdatesTo(this);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientVH holder, int position) {
        Client client = clientArrayList.get(position);
        holder.itemBinding.setClient(client);
    }

    @Override
    public int getItemCount() {
        return clientArrayList == null ? 0 : clientArrayList.size();
    }

    public class ClientVH extends RecyclerView.ViewHolder {
        private final ClientItemBinding itemBinding;


        public ClientVH(@NonNull ClientItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }
}
