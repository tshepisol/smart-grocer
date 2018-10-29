package za.co.soma.solutions.smart.grocer.referral;

import java.math.BigDecimal;

public class CustomerReferral {
    Long customerId;
    int paidCustomerCount;
   // BigDecimal


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getPaidCustomerCount() {
        return paidCustomerCount;
    }

    public void incrementCustomerCount(){
        paidCustomerCount++;
    }
}
