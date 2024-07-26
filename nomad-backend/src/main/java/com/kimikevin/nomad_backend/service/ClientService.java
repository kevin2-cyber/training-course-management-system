package com.kimikevin.nomad_backend.service;

import com.kimikevin.nomad_backend.model.Client;
import com.kimikevin.nomad_backend.repository.ClientRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Long getNextSequenceValue(String sequenceName) {
        BigInteger nextValue = (BigInteger) entityManager.createNativeQuery("SELECT NEXTVAL(:seq_client_no)")
                .setParameter(sequenceName, sequenceName)
                .getSingleResult();
        return nextValue.longValue();
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));
    }

    public Client updateClient(Long id, Client clientDetails) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));

        client.setClientName(clientDetails.getClientName());
        client.setClientAddress(client.getClientAddress());
        client.setClientPhone(clientDetails.getClientPhone());

        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
