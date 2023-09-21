package dev.ngdangkiet.pingpongrestfulapi.customer.service;

import dev.ngdangkiet.pingpongrestfulapi.customer.model.CustomerEntity;
import dev.ngdangkiet.pingpongrestfulapi.customer.repository.CustomerRepository;
import dev.ngdangkiet.pingpongrestfulapi.exception.DuplicateResourceException;
import dev.ngdangkiet.pingpongrestfulapi.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ngdangkiet
 * @since 9/20/2023
 */

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public List<CustomerEntity> findAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerEntity findCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("customerId", id));
    }

    @Override
    public void insertCustomer(CustomerEntity entity) {
        validateUpsertCustomer(entity);
        customerRepository.save(entity);
    }

    @Override
    public void updateCustomer(CustomerEntity entity) {
        if (!existCustomerById(entity.getId())) {
            throw new ResourceNotFoundException("customerId", entity.getId());
        }
        validateUpsertCustomer(entity);
    }

    @Override
    public void deleteCustomerById(Long id) {
        if (!existCustomerById(id)) {
            throw new ResourceNotFoundException("customerId", id);
        }
        customerRepository.deleteById(id);
    }

    @Override
    public boolean existCustomerById(Long id) {
        return customerRepository.existCustomerById(id);
    }

    @Override
    public boolean existCustomerByEmail(String email) {
        return customerRepository.existCustomerByEmail(email);
    }

    private void validateUpsertCustomer(CustomerEntity entity) {
        if (customerRepository.existCustomerByEmail(entity.getEmail())) {
            throw new DuplicateResourceException("customerEmail", entity.getEmail());
        }
    }
}
