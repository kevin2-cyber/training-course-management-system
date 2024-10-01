package com.mukeju.nomad.client;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.ArrayList;
import java.util.Objects;

public class ClientDiffCallback extends DiffUtil.Callback {
    ClientList oldClients;
    ClientList newClients;

    public ClientDiffCallback(ClientList oldClients, ClientList newClients) {
        this.oldClients = oldClients;
        this.newClients = newClients;
    }

    @Override
    public int getOldListSize() {
        return oldClients == null ? 0 : oldClients.size();
    }

    @Override
    public int getNewListSize() {
        return newClients == null? 0 : newClients.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return Objects.equals(oldClients.get(oldItemPosition).getClientNo(), newClients.get(newItemPosition).getClientNo());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return Objects.equals(oldClients.get(oldItemPosition), newClients.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
