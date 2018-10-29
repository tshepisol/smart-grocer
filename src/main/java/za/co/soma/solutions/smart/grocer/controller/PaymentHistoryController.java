package za.co.soma.solutions.smart.grocer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.soma.solutions.smart.grocer.Service.CustomerService;
import za.co.soma.solutions.smart.grocer.Service.PaymentHistoryService;
import za.co.soma.solutions.smart.grocer.domain.Customer;
import za.co.soma.solutions.smart.grocer.domain.PaymentHistory;
import za.co.soma.solutions.smart.grocer.exception.GrocerErrorType;

import java.util.List;
import java.util.Optional;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/customer/payment")
public class PaymentHistoryController {

    public static  final Logger log = LoggerFactory.getLogger(PaymentHistoryController.class);

    @Autowired
    PaymentHistoryService paymentHistoryService;


    @GetMapping("/{id}")
    public ResponseEntity<?> retrieve(@PathVariable("id") Long customerId){

        log.info("retrieve payment history for customer id: {}", customerId);

        List<PaymentHistory> paymentHistoryList =paymentHistoryService.retrieve(customerId);

        if(!paymentHistoryList.isEmpty()){
            return new ResponseEntity(paymentHistoryList, HttpStatus.OK);
        }

        log.warn("PaymentHistory  for customer id [] not found", customerId);

        return new ResponseEntity(new GrocerErrorType("PaymentHistory for customer "+customerId+" not found"), HttpStatus.OK);
    }

}
