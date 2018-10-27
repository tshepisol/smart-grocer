package za.co.soma.solutions.smart.grocer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.soma.solutions.smart.grocer.Service.CustomerService;
import za.co.soma.solutions.smart.grocer.Service.SomaValidation;
import za.co.soma.solutions.smart.grocer.domain.Customer;
import za.co.soma.solutions.smart.grocer.domain.validator.Registration;
import za.co.soma.solutions.smart.grocer.exception.GrocerErrorType;

import javax.validation.Validator;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/register")
public class RegisterController implements SomaValidation {

    public static  final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerService customerService;

    @Autowired
    Validator validator;


    @PostMapping
    public ResponseEntity<?> register(@RequestBody Customer customer){

        log.info("creating customer: {}", customer);

        if(customer.getId() != null && customerService.retrieve(customer.getId()).isPresent()){
            log.warn("unable to register customer. customer already exist: {}", customer);
            return new ResponseEntity(new GrocerErrorType("Unable to create. customer exist: "+ customer), HttpStatus.BAD_REQUEST);
        }

        GrocerErrorType grocerErrorType = validate(validator, customer, Registration.class);
        if(grocerErrorType != null){
            log.warn("unable to save customer. validation failed: {}", customer);
            return new ResponseEntity(grocerErrorType, HttpStatus.BAD_REQUEST);
        }

        customer = customerService.create(customer);

        return new ResponseEntity(customer, HttpStatus.OK);
    }

}
