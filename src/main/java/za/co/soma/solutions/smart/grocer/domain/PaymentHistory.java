package za.co.soma.solutions.smart.grocer.domain;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "PAYMENT_HISTORY")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, scope = PaymentHistory.class, property = "id")
public class PaymentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_no_id")
    private BankDetail bankDetail;


    private Integer month;

    private Integer year;

    private String reference;

    private double amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatusType paymentStatus;

    @ManyToOne
    @JoinColumn(referencedColumnName = "hamperReference")
    private CustomerHamper customerHamper;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BankDetail getBankDetail() {
        return bankDetail;
    }

    public void setBankDetail(BankDetail bankDetail) {
        this.bankDetail = bankDetail;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentStatusType getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatusType paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public CustomerHamper getCustomerHamper() {
        return customerHamper;
    }

    public void setCustomerHamper(CustomerHamper customerHamper) {
        this.customerHamper = customerHamper;
    }
}
