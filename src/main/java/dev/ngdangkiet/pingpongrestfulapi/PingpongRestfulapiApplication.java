package dev.ngdangkiet.pingpongrestfulapi;

import com.github.javafaker.Faker;
import dev.ngdangkiet.pingpongrestfulapi.customer.model.CustomerEntity;
import dev.ngdangkiet.pingpongrestfulapi.customer.model.Gender;
import dev.ngdangkiet.pingpongrestfulapi.customer.repository.CustomerRepository;
import dev.ngdangkiet.pingpongrestfulapi.security.CustomPasswordEncoder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class PingpongRestfulapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PingpongRestfulapiApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomPasswordEncoder customPasswordEncoder,
                                        CustomerRepository customerRepository) {
        return args -> {
            createRandomCustomer(customPasswordEncoder, customerRepository);
        };
    }

    private static void createRandomCustomer(CustomPasswordEncoder customPasswordEncoder,
                                             CustomerRepository customerRepository) {
        Faker faker = new Faker();
        String name = faker.name().fullName();
        int age = new Random().nextInt(18, 60);
        CustomerEntity customerEntity = new CustomerEntity(
                name,
                String.format("%s@ngdangkiet.com", name),
                age,
                age % 2 == 0 ? Gender.MALE : Gender.FEMALE,
                customPasswordEncoder.encode("Prot3ct3d")
        );
        customerRepository.save(customerEntity);
    }
}
