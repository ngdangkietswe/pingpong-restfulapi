package dev.ngdangkiet.pingpongrestfulapi.customer.service;

import dev.ngdangkiet.pingpongrestfulapi.customer.model.CustomerDTO;
import dev.ngdangkiet.pingpongrestfulapi.customer.payload.CustomerInsertRequest;
import dev.ngdangkiet.pingpongrestfulapi.customer.payload.CustomerUpdateRequest;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * @author ngdangkiet
 * @since 9/20/2023
 */

public interface ICustomerService {
    List<CustomerDTO> findAllCustomer();

    CustomerDTO findCustomerById(Long id);

    void insertCustomer(CustomerInsertRequest customer);

    void updateCustomer(Long id, CustomerUpdateRequest customer);

    void deleteCustomerById(Long id);

    ByteArrayInputStream export2CSV();
}
