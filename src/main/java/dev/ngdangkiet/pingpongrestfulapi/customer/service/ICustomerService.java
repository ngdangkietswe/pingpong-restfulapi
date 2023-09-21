package dev.ngdangkiet.pingpongrestfulapi.customer.service;

import dev.ngdangkiet.pingpongrestfulapi.customer.model.CustomerEntity;

import java.util.List;

/**
 * @author ngdangkiet
 * @since 9/20/2023
 */

public interface ICustomerService {
    List<CustomerEntity> findAllCustomer();

    CustomerEntity findCustomerById(Long id);

    void insertCustomer(CustomerEntity entity);

    void updateCustomer(CustomerEntity entity);

    void deleteCustomerById(Long id);

    boolean existCustomerById(Long id);

    boolean existCustomerByEmail(String email);
}
