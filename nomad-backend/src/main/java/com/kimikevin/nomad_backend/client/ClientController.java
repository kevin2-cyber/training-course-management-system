package com.kimikevin.nomad_backend.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/clients")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client createdClient = clientService.createClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }

    @GetMapping("{clientNo}")
    public ResponseEntity<Client> getClientById(@PathVariable Long clientNo) {
        Client client = clientService.getClientById(clientNo);
        return ResponseEntity.ok(client);
    }

    @PutMapping("{clientNo}")
    public ResponseEntity<Client> updateClient(@PathVariable Long clientNo, @RequestBody Client clientDetails) {
        Client updatedClient = clientService.updateClient(clientNo, clientDetails);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("{clientNo}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long clientNo) {
        clientService.deleteClient(clientNo);
        return ResponseEntity.noContent().build();
    }
}