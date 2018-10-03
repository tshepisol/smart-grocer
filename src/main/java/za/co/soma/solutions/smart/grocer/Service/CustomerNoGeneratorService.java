package za.co.soma.solutions.smart.grocer.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.soma.solutions.smart.grocer.dao.CustomerNoRepository;
import za.co.soma.solutions.smart.grocer.domain.CustomerNo;

import java.util.List;

@Service
public class CustomerNoGeneratorService {

    @Autowired
    CustomerNoRepository customerNoRepository;

    public int getCustomerNo(){

        List<CustomerNo>  customerNos = customerNoRepository.findAll();
        customerNoRepository.save(customerNos.get(0));
        customerNoRepository.flush();

        return customerNos.get(0).getCustomerNumber();
    }
}
