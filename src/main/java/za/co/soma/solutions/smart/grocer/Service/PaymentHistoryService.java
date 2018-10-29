package za.co.soma.solutions.smart.grocer.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.soma.solutions.smart.grocer.domain.Customer;
import za.co.soma.solutions.smart.grocer.domain.PaymentHistory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentHistoryService {

    @Autowired
    CustomerService customerService;


    public List<PaymentHistory> retrieve(Long customerId){
        Optional<Customer> customerOptional = customerService.retrieve(customerId);

        List<PaymentHistory> paymentHistories = new ArrayList<>();
        if(customerOptional.isPresent())
            customerOptional.get().getBankDetails().stream().forEach(bankDetail -> {
                paymentHistories.addAll(bankDetail.getPaymentHistoryList());
            });

        return paymentHistories;
    }
}
