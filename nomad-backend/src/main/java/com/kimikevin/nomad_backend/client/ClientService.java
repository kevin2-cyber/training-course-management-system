package com.kimikevin.nomad_backend.client;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client getClientById(Long clientNo) {
        return clientRepository.findById(clientNo)
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));
    }

    public Client updateClient(Long clientNo, Client clientDetails) {
        Client client = getClientById(clientNo);

        client.setClientName(clientDetails.getClientName());
        client.setClientAddress(client.getClientAddress());
        client.setClientPhone(clientDetails.getClientPhone());

        return clientRepository.save(client);
    }

    public void deleteClient(Long clientNo) {
        clientRepository.deleteById(clientNo);
    }
}
