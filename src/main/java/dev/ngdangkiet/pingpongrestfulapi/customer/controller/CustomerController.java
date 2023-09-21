package dev.ngdangkiet.pingpongrestfulapi.customer.controller;

import dev.ngdangkiet.pingpongrestfulapi.customer.payload.CustomerInsertRequest;
import dev.ngdangkiet.pingpongrestfulapi.customer.payload.CustomerUpdateRequest;
import dev.ngdangkiet.pingpongrestfulapi.customer.service.ICustomerService;
import dev.ngdangkiet.pingpongrestfulapi.payload.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ngdangkiet
 * @since 9/20/2023
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {
    private final ICustomerService customerService;

    @GetMapping
    public Response<Object> findAllCustomer() {
        return new Response<>(customerService.findAllCustomer());
    }

    @GetMapping("/{id}")
    public Response<Object> findCustomerById(@PathVariable Long id) {
        return new Response<>(customerService.findCustomerById(id));
    }

    @PostMapping
    public Response<Object> insertCustomer(@RequestBody CustomerInsertRequest insertRequest) {
        customerService.insertCustomer(insertRequest);
        return new Response<>(null);
    }

    @PutMapping("/{id}")
    public Response<Object> updateCustomer(@PathVariable Long id, @RequestBody CustomerUpdateRequest updateRequest) {
        customerService.updateCustomer(id, updateRequest);
        return new Response<>(null);
    }

    @DeleteMapping("/{id}")
    public Response<Object> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return new Response<>(null);
    }

    @GetMapping("/csv")
    public ResponseEntity<Resource> export2CSV() {
        String fileName = "customers.csv";
        InputStreamResource inputStreamResource = new InputStreamResource(customerService.export2CSV());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s", fileName))
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(inputStreamResource);
    }
}
