package com.kimikevin.nomad_backend.service;

import com.kimikevin.nomad_backend.model.Client;
import com.kimikevin.nomad_backend.model.Employee;
import com.kimikevin.nomad_backend.repository.ClientRepository;
import com.kimikevin.nomad_backend.repository.EmployeeRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    ClientRepository clientRepository;

    EmployeeRepository employeeRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientRepository.findByClientEmail(username);

        if (client != null) {
            return User.builder()
                    .username(client.getClientEmail())
                    .password(client.getClientPassword())
                    .roles("CLIENT")
                    .build();
        }

        Employee employee = employeeRepository.findByEmployeeEmail(username);

        if (employee != null) {
            return User.builder()
                    .username(employee.getEmployeeEmail())
                    .password(employee.getEmployeePassword())
                    .roles("EMPLOYEE")
                    .build();
        }
        throw new UsernameNotFoundException("User not found");
    }
}
