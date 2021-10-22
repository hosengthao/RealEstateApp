package net.yorksolutions.realestate.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.yorksolutions.realestate.model.Customer;
import net.yorksolutions.realestate.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/customer")
@RestController
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/all")
    String getAllCustomers() throws JsonProcessingException {
        return objectMapper.writeValueAsString(customerRepository.findAll());
    }

    @GetMapping("/getByRowAmount")
    String getCustomersByRow(@RequestParam("rows") String rows) throws JsonProcessingException {
        List<Customer> customerList = (List<Customer>) customerRepository.findAll();

        customerList = customerList.stream().limit(Long.parseLong(rows)).collect(Collectors.toList());

        return objectMapper.writeValueAsString(customerList);
    }

    @PostMapping("/add")
    String putRealEstate(@RequestBody String body) {
        try {
            Customer newCustomer = objectMapper.readValue(body,Customer.class);
            customerRepository.save(newCustomer);
        } catch (JsonProcessingException e) {
            return e.getMessage();
        }

        return "successfully added customer";
    }

    @DeleteMapping("/delete")
    String delCustomer(@RequestParam("id") Long id) {
        try {
            customerRepository.deleteById(id);
        } catch (Exception e){
            return "error";
        }
        return "deleted";
    }

}
