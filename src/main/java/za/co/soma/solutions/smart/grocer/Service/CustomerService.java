package za.co.soma.solutions.smart.grocer.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import za.co.soma.solutions.smart.grocer.dao.CustomerRepository;
import za.co.soma.solutions.smart.grocer.dao.RoleRepository;
import za.co.soma.solutions.smart.grocer.domain.Customer;
import za.co.soma.solutions.smart.grocer.domain.Role;
import za.co.soma.solutions.smart.grocer.domain.RoleName;
import za.co.soma.solutions.smart.grocer.domain.User;

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

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    private static final String PREFIX_GROCER = "SS";



    public Optional<Customer> retrieve(Long customerId){
        return  customerRepository.findById(customerId);
    }


    public List<Customer> getAll(){
        return  customerRepository.findAll();
    }


    public Customer create(Customer customer){

        if(customer.getCustomerNo() == null)
            createDefaultCustomer(customer);

        return customerRepository.save(customer);
    }

    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer createCustomerHamper(Customer customer){
        customerReferral(customer);
        createDefaultCustomerHanper(customer);

        return  customerRepository.save(customer);
    }

    public Customer update(Customer customer){
        return customerRepository.save(customer);
    }


    public void delete(Long customerId){
        customerRepository.deleteById(customerId);
    }

    public Customer getCustomerByIdJoinFetchCustomerReferral(Long customerId){
        return customerRepository.getCustomerByIdJoinFetchCustomerReferral(customerId);
    }

    public Customer getByCustomerNo(String customerNo){
        return  customerRepository.getByCustomerNo(customerNo);
    }


    private void createDefaultCustomer(Customer customer){

        customer.getUser().setCustomer(customer);
        encryptPassword(customer.getUser());

        customerReferral(customer);

        Role customerRole = roleRepository.findByRoleName(RoleName.CUSTOMER);
        customer.getUser().getRoles().add(customerRole);

        int customerNo = customerNoGeneratorService.getCustomerNo();
        System.out.println("Customer NO:"+customer);
        customer.setCustomerNo(PREFIX_GROCER + customerNo);

        defaultRelationships(customer);


    }


    private void createDefaultCustomerHanper(Customer customer){

        customerReferral(customer);
        defaultRelationships(customer);
    }


    private void defaultRelationships(Customer customer){
        customer.getCustomerAddresses().stream().forEach(customerAddress -> {
            if(customerAddress.getCustomer() == null)
                customerAddress.setCustomer(customer);
        });

        customer.getCustomerContacts().stream().forEach(customerContact -> {
            if(customerContact.getCustomer() == null)
                customerContact.setCustomer(customer);
        });

        customer.getBankDetails().stream().forEach(bankDetail -> {
            if(bankDetail.getCustomer() == null)
                bankDetail.setCustomer(customer);
        });

        customer.getCustomerHampers().stream().forEach( customerHamper -> {
            customerHamper.setCustomer(customer);
        });
    }

    private void customerReferral(Customer customer){
        if(customer.getCustomerReferral() != null ) {
            Optional<Customer> customerReferredOptional = customerRepository.findById(customer.getCustomerReferral().getId());
            if(customerReferredOptional.isPresent())
                customer.setCustomerReferral(customerReferredOptional.get());
            else
                customer.setCustomerReferral(null);
        }
    }

    public List<Customer> referrals(Long customerId){
       return customerRepository.getCustomersByCustomerReferralId(customerId);
    }

    private void encryptPassword(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    }

}
