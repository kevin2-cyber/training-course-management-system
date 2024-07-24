//package com.kimikevin.nomad_backend.controller;
//
//import com.kimikevin.nomad_backend.model.Client;
//import com.kimikevin.nomad_backend.model.Employee;
//import com.kimikevin.nomad_backend.repository.ClientRepository;
//import com.kimikevin.nomad_backend.repository.EmployeeRepository;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    ClientRepository clientRepository;
//
//    EmployeeRepository employeeRepository;
//
//    PasswordEncoder passwordEncoder;
//
//    @PostMapping
//    public ResponseEntity<Client> registerClient(@RequestBody Client client) {
//        client.setClientPassword(passwordEncoder.encode(client.getClientPassword()));
//        return ResponseEntity.status(HttpStatus.CREATED).body(clientRepository.save(client));
//    }
//
//    @PostMapping
//    public ResponseEntity<Employee> registerEmployee(@RequestBody Employee employee) {
//        employee.setEmployeePassword(passwordEncoder.encode(employee.getEmployeePassword()));
//        return ResponseEntity.status(HttpStatus.CREATED).body(employeeRepository.save(employee));
//    }
//
//}
