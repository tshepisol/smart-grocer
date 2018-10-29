package za.co.soma.solutions.smart.grocer.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.soma.solutions.smart.grocer.dao.CustomerRepository;
import za.co.soma.solutions.smart.grocer.dao.PartnerRepository;
import za.co.soma.solutions.smart.grocer.dao.PaymentHistoryRepository;
import za.co.soma.solutions.smart.grocer.domain.Customer;
import za.co.soma.solutions.smart.grocer.domain.PaymentHistory;
import za.co.soma.solutions.smart.grocer.referral.CustomerReferral;
import za.co.soma.solutions.smart.grocer.referral.PartnerClassifier;

import javax.transaction.Transactional;
import java.util.*;

@Component
@Transactional
public class CustomerPartnerLevelService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PaymentHistoryRepository paymentHistoryRepository;

    @Autowired
    PartnerRepository partnerRepository;


    @Autowired
    PartnerClassifier partnerClassifier;

    Map<Long, CustomerReferral> customerReferralMap = new HashMap<>();

    public void removePartnerLevel() {
        partnerRepository.deleteAllPartnerLevel();
    }

    public List<PaymentHistory> history() {

        List<PaymentHistory> paymentHistories = paymentHistoryRepository.findByYearMonth(2018, 10);

        Customer customer = paymentHistories.get(0).getBankDetail().getCustomer();
        System.out.println("customer:" + customer.getId());

        return paymentHistories;
    }


    public void determinePartnerLevel() {


        Calendar date = Calendar.getInstance();
        date.setTime(new Date());

        int month = getCurrentMonth(date) + 1;

        int year = getCurrentYear(date);

        List<PaymentHistory> currentPaymentHistories = paymentHistoryRepository.findByYearMonth(year, month);

        currentPaymentHistories.stream().forEach(paymentHistory -> {

            Long referralId = null;
            if(paymentHistory.getBankDetail().getCustomer().getCustomerReferral() != null)
                referralId = paymentHistory.getBankDetail().getCustomer().getCustomerReferral().getId();

            if (referralId != null) {
                if (customerReferralMap.containsKey(referralId)) {
                    CustomerReferral customerReferral = customerReferralMap.get(referralId);
                    customerReferral.incrementCustomerCount();
                } else {
                    CustomerReferral customerReferral = new CustomerReferral();
                    customerReferral.setCustomerId(referralId);
                    customerReferral.incrementCustomerCount();
                    customerReferralMap.put(referralId, customerReferral);
                }
            }
        });

        partnerClassifier.classify(customerReferralMap);

    }

    private Long getReferral(Customer customer) {
        Customer customerReferral = customerRepository.getCustomerByIdJoinFetchCustomerReferral(customer.getId());
        if (customerReferral.getCustomerReferral() != null)
            return customerReferral.getCustomerReferral().getId();
        else
            return null;
    }

    private int getCurrentMonth(Calendar calendar) {
        return calendar.get(Calendar.MONDAY);
    }

    private int getCurrentYear(Calendar date) {
        return date.get(Calendar.YEAR);
    }


    public void getPartnerLevel(int customerCount) {
    }
}
