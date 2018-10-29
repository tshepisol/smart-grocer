package za.co.soma.solutions.smart.grocer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.soma.solutions.smart.grocer.Service.HamperService;
import za.co.soma.solutions.smart.grocer.dao.HamperRepository;
import za.co.soma.solutions.smart.grocer.domain.Customer;
import za.co.soma.solutions.smart.grocer.domain.Hamper;
import za.co.soma.solutions.smart.grocer.exception.GrocerErrorType;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/hamper")
public class HamperController {

    public static  final Logger log = LoggerFactory.getLogger(HamperController.class);

    @Autowired
    HamperService hamperService;

    @GetMapping
    public ResponseEntity<?> retrieveAll(){
        List<Hamper> hampers =  hamperService.getAll();

        if(hampers.isEmpty()){
            return  new ResponseEntity(new GrocerErrorType("No hampers found"), HttpStatus.OK);
        }

        return new ResponseEntity(hampers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieve(@PathVariable("id") Long hamperId){

        log.info("retrieve hamper id: {}", hamperId);

        Optional<Hamper> customerOptional = hamperService.retrieve(hamperId);

        if(customerOptional.isPresent()){
            return new ResponseEntity(customerOptional.get(), HttpStatus.OK);
        }

        log.warn("Hamper with id [] not found", hamperId);

        return new ResponseEntity(new GrocerErrorType("Hamper with id "+hamperId+" not found"), HttpStatus.OK);
    }



}
