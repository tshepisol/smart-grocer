package za.co.soma.solutions.smart.grocer.referral;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.soma.solutions.smart.grocer.dao.CustomerRepository;
import za.co.soma.solutions.smart.grocer.domain.Customer;
import za.co.soma.solutions.smart.grocer.domain.Partner;
import za.co.soma.solutions.smart.grocer.domain.PartnerType;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

@Transactional
@Component
public class PartnerClassifier {

    @Autowired
    CustomerRepository customerRepository;

    public void classify(Map<Long, CustomerReferral> customerReferralMap){

        for (Long customerId :customerReferralMap.keySet()){
            CustomerReferral customerReferral = customerReferralMap.get(customerId);

            Optional<Customer>  customerPartnerOptional = customerRepository.findById(customerId);
            Customer customer = customerPartnerOptional.get();
            setPartnerLevel(customer, customerReferral.getPaidCustomerCount());

            Customer customer1 = customerRepository.save(customer);

            System.out.println("PARTNER:"+customer1.getId()+" NAME:"+customer.getLastName()+" LEVEL:"+customer.getPartner().getPartnerType());

        }

    }


    private Partner setPartnerLevel(Customer customerPartner, int paidCount){

        /* LEVEL_1, //10
        LEVEL_2, //100
        LEVEL_3, //500
        LEVEL_4, //1000
        LEVEL_5,  //3000
        LEVEL_6, //5000
        LEVEL_7, //8000
        LEVEL_8, //10 000
        LEVEL_9, //50 000
        LEVEL_10  //100 000
        */

        if(paidCount >= 1){
            return createPartner(customerPartner, paidCount,PartnerType.LEVEL_1);
        }else  if(paidCount >=100){
            return createPartner(customerPartner, paidCount,PartnerType.LEVEL_2);
        }else  if(paidCount >=500){
            return createPartner(customerPartner, paidCount,PartnerType.LEVEL_3);
        }else  if(paidCount >=1000){
            return createPartner(customerPartner, paidCount,PartnerType.LEVEL_4);
        }else  if(paidCount >=3000){
            return createPartner(customerPartner, paidCount,PartnerType.LEVEL_5);
        }else  if(paidCount >=5000){
            return createPartner(customerPartner, paidCount,PartnerType.LEVEL_6);
        }else  if(paidCount >=8000){
            return createPartner(customerPartner, paidCount,PartnerType.LEVEL_7);
        }else  if(paidCount >=10000){
            return createPartner(customerPartner, paidCount,PartnerType.LEVEL_8);
        }else  if(paidCount >=50000) {
            return createPartner(customerPartner, paidCount, PartnerType.LEVEL_9);
        }else  if(paidCount > 100000) {
            return createPartner(customerPartner, paidCount, PartnerType.LEVEL_10);
        }


        return null;
    }

    private  Partner createPartner(Customer customerPartner, int paidCount, PartnerType partnerType){
        Partner partner = new Partner();
        partner.setReferral(paidCount);
        partner.setCustomer(customerPartner);
        partner.setPartnerType(partnerType);
        customerPartner.setPartner(partner);
        return partner;
    }


}
