package za.co.soma.solutions.smart.grocer.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import za.co.soma.solutions.smart.grocer.Service.CustomerService;
import za.co.soma.solutions.smart.grocer.Service.SomaValidation;
import za.co.soma.solutions.smart.grocer.domain.Customer;
import za.co.soma.solutions.smart.grocer.domain.validator.Registration;
import za.co.soma.solutions.smart.grocer.exception.GrocerErrorType;

import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController implements SomaValidation {

    public static  final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerService customerService;

    @Autowired
    Validator validator;



    @GetMapping
    public ResponseEntity<List<Customer>> retrieveAll(){
        List<Customer> customers =  customerService.getAll();

        if(customers.isEmpty()){
            return  new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieve(@PathVariable("id") Long customerId){

        log.info("retrieve customer id: {}", customerId);

        Optional<Customer> customerOptional = customerService.retrieve(customerId);

        if(customerOptional.isPresent()){
            return new ResponseEntity(customerOptional.get(), HttpStatus.OK);
        }

        log.warn("Customer with id [] not found", customerId);

        return new ResponseEntity(new GrocerErrorType("Customer with id "+customerId+" not found"), HttpStatus.NOT_FOUND);
    }


    @PostMapping
    public ResponseEntity<?> create(@RequestBody Customer customer, UriComponentsBuilder uriComponentsBuilder){

        log.info("creating customer: {}", customer);

        if(customer.getId() != null && customerService.retrieve(customer.getId()).isPresent()){
            log.warn("unable to save customer. customer already exist: {}", customer);
            return new ResponseEntity(new GrocerErrorType("Unable to create. customer exist: "+ customer), HttpStatus.BAD_REQUEST);
        }

        GrocerErrorType grocerErrorType = validate(validator, customer, Registration.class);
        if(grocerErrorType != null){
            log.warn("unable to save customer. validation failed: {}", customer);
            return new ResponseEntity(grocerErrorType, HttpStatus.BAD_REQUEST);
        }

        customer = customerService.register(customer);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uriComponentsBuilder.path("/customer/{id}").buildAndExpand(customer.getId()).toUri());

        return new ResponseEntity<String>(httpHeaders, HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Customer customer){

        log.info("update customer: {}", customer);

        if(customer.getId() == null && !customerService.retrieve(customer.getId()).isPresent()){
            log.warn("unable to update customer. customer doesnt exist: {}", customer);
            return new ResponseEntity(new GrocerErrorType("Unable to update. customer doesnt exist: "+ customer), HttpStatus.BAD_REQUEST);
        }

        customer = customerService.update(customer);

        return new ResponseEntity(customer, HttpStatus.OK);
    }

    @DeleteMapping
    public void delete(@PathVariable("id") Long customerId){

        log.info("delete customer: {}", customerId);

        if(customerId == null && customerService.retrieve(customerId).isPresent()){
            log.warn("unable to delete customer. customer doesnt exist: {}", customerId);
            throw new RuntimeException("Unable to delete. customer doesnt exist: "+ customerId);
        }

        customerService.delete(customerId);

    }

}
