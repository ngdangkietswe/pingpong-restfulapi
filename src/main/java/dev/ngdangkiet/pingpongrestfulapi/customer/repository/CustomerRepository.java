package dev.ngdangkiet.pingpongrestfulapi.customer.repository;

import dev.ngdangkiet.pingpongrestfulapi.base.IBaseRepository;
import dev.ngdangkiet.pingpongrestfulapi.customer.model.CustomerEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author ngdangkiet
 * @since 9/20/2023
 */

@Repository
public interface CustomerRepository extends IBaseRepository<CustomerEntity> {
    boolean existCustomerById(Long id);

    boolean existCustomerByEmail(String email);

    Optional<CustomerEntity> findCustomerByEmail(String email);
}
