package dev.ngdangkiet.pingpongrestfulapi.customer.service;

import dev.ngdangkiet.pingpongrestfulapi.customer.model.CustomerDTO;
import dev.ngdangkiet.pingpongrestfulapi.customer.model.CustomerEntity;
import dev.ngdangkiet.pingpongrestfulapi.customer.model.CustomerMapper;
import dev.ngdangkiet.pingpongrestfulapi.customer.model.Gender;
import dev.ngdangkiet.pingpongrestfulapi.customer.payload.CustomerInsertRequest;
import dev.ngdangkiet.pingpongrestfulapi.customer.payload.CustomerUpdateRequest;
import dev.ngdangkiet.pingpongrestfulapi.customer.repository.CustomerRepository;
import dev.ngdangkiet.pingpongrestfulapi.exception.DuplicateResourceException;
import dev.ngdangkiet.pingpongrestfulapi.exception.RequestValidationException;
import dev.ngdangkiet.pingpongrestfulapi.exception.ResourceNotFoundException;
import dev.ngdangkiet.pingpongrestfulapi.security.CustomPasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author ngdangkiet
 * @since 9/20/2023
 */

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    private final CustomerRepository customerRepository;
    private final CustomPasswordEncoder customPasswordEncoder;
    private final PasswordEncoder passwordEncoder;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDTO> findAllCustomer() {
        return customerRepository.findAll().stream()
                .map(customerMapper)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO findCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper)
                .orElseThrow(() -> new ResourceNotFoundException("customerId", id));
    }

    @Override
    public void insertCustomer(CustomerInsertRequest insertRequest) {
        validateUpsertCustomer(insertRequest.email());
        CustomerEntity customerEntity = new CustomerEntity(
                insertRequest.name(),
                insertRequest.email(),
                insertRequest.age(),
                Gender.valueOf(insertRequest.gender()),
                passwordEncoder.encode(insertRequest.password())
        );
        customerRepository.save(customerEntity);
    }

    @Override
    public void updateCustomer(Long id, CustomerUpdateRequest updateRequest) {
        CustomerEntity customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("customerId", id));

        boolean changes = false;

        if (Objects.nonNull(updateRequest.name()) && !Objects.equals(updateRequest.name(), customerEntity.getName())) {
            customerEntity.setName(updateRequest.name());
            changes = true;
        }

        if (Objects.nonNull(updateRequest.age()) && !Objects.equals(updateRequest.age(), customerEntity.getAge())) {
            customerEntity.setAge(updateRequest.age());
            changes = true;
        }

        if (Objects.nonNull(updateRequest.email()) && !Objects.equals(updateRequest.email(), customerEntity.getEmail())) {
            customerEntity.setEmail(updateRequest.email());
            changes = true;
        }

        if (!changes) {
            throw new RequestValidationException("No data changes!");
        }

        customerRepository.save(customerEntity);
    }

    @Override
    public void deleteCustomerById(Long id) {
        if (!existsCustomerById(id)) {
            throw new ResourceNotFoundException("customerId", id);
        }
        customerRepository.deleteById(id);
    }

    private boolean existsCustomerById(Long id) {
        return customerRepository.existsCustomerById(id);
    }

    private boolean existsCustomerByEmail(String email) {
        return customerRepository.existsCustomerByEmail(email);
    }

    private void validateUpsertCustomer(String email) {
        if (existsCustomerByEmail(email)) {
            throw new DuplicateResourceException("customerEmail", email);
        }
    }
}
