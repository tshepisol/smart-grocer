package za.co.soma.solutions.grocer.smartgrocer.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.soma.solutions.grocer.smartgrocer.dao.CustomerRepository;
import za.co.soma.solutions.grocer.smartgrocer.domain.Customer;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Optional<Customer> get(Long customerId){

        return  null;
    }


    public List<Customer> getAll(){
        return  null;
    }

    public Customer save(Customer customer){
        return null;
    }

    public Customer update(Customer customer){
        return null;
    }


    public Customer delete(Long customerId){
        return null;
    }



}
