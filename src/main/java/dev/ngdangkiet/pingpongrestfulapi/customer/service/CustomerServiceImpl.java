package dev.ngdangkiet.pingpongrestfulapi.customer.service;

import dev.ngdangkiet.pingpongrestfulapi.cache.RedisUtil;
import dev.ngdangkiet.pingpongrestfulapi.csv.CsvHelper;
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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.time.Duration;
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
    private static final String CUSTOMER_KEY = "customer";
    private final CustomerRepository customerRepository;
    private final CustomPasswordEncoder customPasswordEncoder;
    private final PasswordEncoder passwordEncoder;
    private final CustomerMapper customerMapper;
    private final RedisUtil redisUtil;

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Obj obj = new Obj(1);
        Class<?> clazz = obj.getClass();
        Field field = clazz.getDeclaredField("number");
        field.setAccessible(true);
        field.setInt(obj, 2);
        System.out.println(obj.getNumber());
    }

    @Override
    public List<CustomerDTO> findAllCustomer() {
        List<CustomerDTO> customers = redisUtil.getList(CUSTOMER_KEY, CustomerDTO.class);
        if (!CollectionUtils.isEmpty(customers)) {
            return customers;
        }

        customers = customerRepository.findAll().stream()
                .map(customerMapper)
                .collect(Collectors.toList());
        redisUtil.putList(CUSTOMER_KEY, customers, Duration.ofMinutes(15));

        return customers;
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

    @Override
    public ByteArrayInputStream export2CSV() {
        return CsvHelper.customer2CSV(findAllCustomer());
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

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    static class Obj {
        private int number;
    }
}
