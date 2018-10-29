package za.co.soma.solutions.smart.grocer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import za.co.soma.solutions.smart.grocer.security.service.RoleService;
import za.co.soma.solutions.smart.grocer.Service.SomaValidation;
import za.co.soma.solutions.smart.grocer.domain.Role;
import za.co.soma.solutions.smart.grocer.domain.validator.Registration;
import za.co.soma.solutions.smart.grocer.exception.GrocerErrorType;

import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/role")
public class RoleController implements SomaValidation {

    public static  final Logger log = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    RoleService roleService;

    @Autowired
    Validator validator;



    @GetMapping
    public ResponseEntity<List<Role>> retrieveAll(){
        List<Role> roles =  roleService.getAll();

        if(roles.isEmpty()){
            return  new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(roles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieve(@PathVariable("id") Long roleId){

        log.info("retrieve Role id: {}", roleId);

        Optional<Role> roleOptional = roleService.retrieve(roleId);

        if(roleOptional.isPresent()){
            return new ResponseEntity(roleOptional.get(), HttpStatus.OK);
        }

        log.warn("Role with id [] not found", roleId);

        return new ResponseEntity(new GrocerErrorType("Role with id "+roleId+" not found"), HttpStatus.NOT_FOUND);
    }


    @PostMapping
    public ResponseEntity<?> create(@RequestBody Role Role, UriComponentsBuilder uriComponentsBuilder){

        log.info("creating Role: {}", Role);

        if(Role.getId() != null && roleService.retrieve(Role.getId()).isPresent()){
            log.warn("unable to save Role. Role already exist: {}", Role);
            return new ResponseEntity(new GrocerErrorType("Unable to create. Role exist: "+ Role), HttpStatus.BAD_REQUEST);
        }

        GrocerErrorType grocerErrorType = validate(validator, Role, Registration.class);
        if(grocerErrorType != null){
            log.warn("unable to save Role. validation failed: {}", Role);
            return new ResponseEntity(grocerErrorType, HttpStatus.BAD_REQUEST);
        }

        Role = roleService.create(Role);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uriComponentsBuilder.path("/Role/{id}").buildAndExpand(Role.getId()).toUri());

        return new ResponseEntity<String>(httpHeaders, HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Role Role){

        log.info("update Role: {}", Role);

        if(Role.getId() == null && !roleService.retrieve(Role.getId()).isPresent()){
            log.warn("unable to update Role. Role doesnt exist: {}", Role);
            return new ResponseEntity(new GrocerErrorType("Unable to update. Role doesnt exist: "+ Role), HttpStatus.BAD_REQUEST);
        }

        Role = roleService.update(Role);

        return new ResponseEntity(Role, HttpStatus.OK);
    }

    @DeleteMapping
    public void delete(@PathVariable("id") Long roleId){

        log.info("delete Role: {}", roleId);

        if(roleId == null && roleService.retrieve(roleId).isPresent()){
            log.warn("unable to delete Role. Role doesnt exist: {}", roleId);
            throw new RuntimeException("Unable to delete. Role doesnt exist: "+ roleId);
        }

        roleService.delete(roleId);

    }
}
