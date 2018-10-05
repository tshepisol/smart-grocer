package za.co.soma.solutions.smart.grocer.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.soma.solutions.smart.grocer.dao.CustomerRepository;
import za.co.soma.solutions.smart.grocer.domain.Customer;

import java.util.List;

@Component
public class CustomerPartnerLevelService {

    @Autowired
    CustomerRepository customerRepository;

    public void determinePatnerLevel(){
        List<Customer> customers = customerRepository.findAll();


        customers.stream().forEach(customer -> {

            customerRepository.getCustomersByCustomerRefereralId(customer.getId());

        });


    }

    public void getPartnerLevel(int customerCount){}
}
