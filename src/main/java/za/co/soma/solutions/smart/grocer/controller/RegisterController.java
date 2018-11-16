package za.co.soma.solutions.smart.grocer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.soma.solutions.smart.grocer.Service.CustomerService;
import za.co.soma.solutions.smart.grocer.Service.HamperGeneratorService;
import za.co.soma.solutions.smart.grocer.Service.SomaValidation;
import za.co.soma.solutions.smart.grocer.domain.Customer;
import za.co.soma.solutions.smart.grocer.domain.validator.HamperRegistration;
import za.co.soma.solutions.smart.grocer.domain.validator.Registration;
import za.co.soma.solutions.smart.grocer.exception.GrocerErrorType;

import javax.validation.Validator;
import java.io.IOException;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/")
public class RegisterController implements SomaValidation {

    public static  final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerService customerService;

    @Autowired
    HamperGeneratorService hamperGeneratorService;

    @Autowired
    Validator validator;

    @Autowired
    ObjectMapper mapper;


    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody String payload){



        Customer customer = null;
        try {
            customer =   mapper.readValue(payload, Customer.class);
        } catch (IOException e) {
            e.printStackTrace();

            return new ResponseEntity(new GrocerErrorType("Transformation error"+ e.getMessage()), HttpStatus.OK);
        }

        log.info("creating customer: {}", customer);

        if(customer.getId() != null && customerService.retrieve(customer.getId()).isPresent()){
            log.warn("unable to register customer. customer already exist: {}", customer);
            return new ResponseEntity(new GrocerErrorType("Unable to create. customer exist: "+ customer), HttpStatus.OK);
        }

        GrocerErrorType grocerErrorType = validate(validator, customer, Registration.class);
        if(grocerErrorType != null){
            log.warn("unable to save customer. validation failed: {}", customer);
            return new ResponseEntity(grocerErrorType, HttpStatus.OK);
        }

        customer = customerService.create(customer);

        return new ResponseEntity(customer, HttpStatus.OK);
    }


    @PutMapping("/register/hamper")
    @PostMapping("/register/hamper")
    public ResponseEntity<?> registerHamper(@RequestBody String payload){


        Customer customer = null;
        try {
            customer =   mapper.readValue(payload, Customer.class);
        } catch (IOException e) {
            e.printStackTrace();

            return new ResponseEntity(new GrocerErrorType("Transformation error"+ e.getMessage()), HttpStatus.OK);
        }

        log.info("creating customer hamper: {}", customer);

       if(customer.getId() == null && customer.getCustomerNo() == null){
            log.warn("customer id or no cannot be NULL: {}", customer);
            return new ResponseEntity(new GrocerErrorType("customer id or no cannot be NULL: "+ customer), HttpStatus.OK);
        }

        GrocerErrorType grocerErrorType = validate(validator, customer, HamperRegistration.class);
        if(grocerErrorType != null){
            log.warn("unable to register customer hamper. validation failed: {}", customer);
            return new ResponseEntity(grocerErrorType, HttpStatus.OK);
        }

        customer = customerService.createCustomerHamper(customer);

        hamperGeneratorService.createCustomerHamper(customer.getCustomerHampers());
        customer.setOnboardComplete(true);




        return new ResponseEntity(customer, HttpStatus.OK);
    }

}
