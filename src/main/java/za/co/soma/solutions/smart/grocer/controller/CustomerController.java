package za.co.soma.solutions.smart.grocer.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.soma.solutions.smart.grocer.Service.CustomerService;
import za.co.soma.solutions.smart.grocer.Service.SomaValidation;
import za.co.soma.solutions.smart.grocer.domain.Customer;
import za.co.soma.solutions.smart.grocer.exception.GrocerErrorType;

import javax.validation.Valid;
import javax.validation.Validator;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/customer")
public class CustomerController implements SomaValidation {

    public static  final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerService customerService;

    @Autowired
    Validator validator;



    @GetMapping
    public ResponseEntity<?> retrieveAll(){
        List<Customer> customers =  customerService.getAll();

        if(customers.isEmpty()){
            return  new ResponseEntity(new GrocerErrorType("No customers found"), HttpStatus.OK);
        }

        return new ResponseEntity(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieve(@PathVariable("id") Long customerId){

        log.info("retrieve customer id: {}", customerId);

        Customer customer = customerService.getCustomerByIdJoinFetchCustomerReferral(customerId);

        if(customer != null){
            return new ResponseEntity(customer, HttpStatus.OK);
        }

        log.warn("Customer with id [] not found", customerId);

        return new ResponseEntity(new GrocerErrorType("Customer with id "+customerId+" not found"), HttpStatus.OK);
    }


    @GetMapping("/ref/{no}")
    public ResponseEntity<?> customerNumber(@PathVariable("no") String  customerNo){

        log.info("retrieve customer No: {}", customerNo);

        Customer customer = customerService.getByCustomerNo(customerNo);

        if(customer != null){
            return new ResponseEntity(customer, HttpStatus.OK);
        }

        log.warn("Customer with no [] not found", customerNo);

        return new ResponseEntity(new GrocerErrorType("Customer with no "+customerNo+" not found"), HttpStatus.OK);
    }




    @PutMapping
    public ResponseEntity<?> update(@RequestBody String payload){

        ObjectMapper mapper = new ObjectMapper();

        Customer customer = null;
        try {
            customer =   mapper.readValue(payload, Customer.class);
        } catch (IOException e) {
            e.printStackTrace();

            return new ResponseEntity(new GrocerErrorType("Transformation error"+ e.getMessage()), HttpStatus.OK);
        }

        log.info("update customer: {}", customer);

        if(customer.getId() == null && !customerService.retrieve(customer.getId()).isPresent()){
            log.warn("unable to update customer. customer doesnt exist: {}", customer);
            return new ResponseEntity(new GrocerErrorType("Unable to update. customer doesnt exist: "+ customer), HttpStatus.OK);
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

    @GetMapping("/referrals/{id}")
    public ResponseEntity<?> referrals(@PathVariable("id") Long customerId){

        log.info("retrieve customer referrals id: {}", customerId);

        List<Customer> customerReferrals = customerService.referrals(customerId);

        if(!customerReferrals.isEmpty()){
            return new ResponseEntity(customerReferrals, HttpStatus.OK);
        }

        log.warn("Customer referrals with id [] not found", customerId);

        return new ResponseEntity(new GrocerErrorType("Customer id "+customerId+" has no referrals"), HttpStatus.OK);
    }


}
