package za.co.soma.solutions.smart.grocer.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.soma.solutions.smart.grocer.dao.CustomerRepository;
import za.co.soma.solutions.smart.grocer.dao.RoleRepository;
import za.co.soma.solutions.smart.grocer.domain.*;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerNoGeneratorService customerNoGeneratorService;

    @Autowired
    RoleRepository roleRepository;

    private static final String PREFIX_GROCER = "GR";


    public Optional<Customer> retrieve(Long customerId){

        return  customerRepository.findById(customerId);
    }


    public List<Customer> getAll(){
        return  null;
    }


    public Customer register(Customer customer){

        createDefaultCustomer(customer);

        return customerRepository.save(customer);
    }

    public Customer update(Customer customer){
        return null;
    }


    public Customer delete(Long customerId){
        return null;
    }


    private void createDefaultCustomer(Customer customer){

        customer.getUser().setCustomer(customer);

        Role customerRole = roleRepository.findByRoleType(RoleType.CUSTOMER);
        customer.getUser().getRoles().add(customerRole);

        int customerNo = customerNoGeneratorService.getCustomerNo();
        System.out.println("Customer NO:"+customer);
        customer.setCustomerNo(PREFIX_GROCER + customerNo);
    }

}
