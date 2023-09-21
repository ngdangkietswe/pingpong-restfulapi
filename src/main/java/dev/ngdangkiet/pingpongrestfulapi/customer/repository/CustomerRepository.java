package dev.ngdangkiet.pingpongrestfulapi.customer.repository;

import dev.ngdangkiet.pingpongrestfulapi.base.IBaseRepository;
import dev.ngdangkiet.pingpongrestfulapi.customer.model.CustomerEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author ngdangkiet
 * @since 9/20/2023
 */

@Repository("customerJpaRepository")
public interface CustomerRepository extends IBaseRepository<CustomerEntity> {
    boolean existsCustomerById(Long id);

    boolean existsCustomerByEmail(String email);

    Optional<CustomerEntity> findCustomerByEmail(String email);
}
