package za.co.soma.solutions.smart.grocer.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CUSTOMER_HAMPER")
@DynamicUpdate
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, scope = CustomerHamper.class, property = "@id")
public class CustomerHamper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull(message = "Customer required - Customer Hamper mapping")
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    @JsonBackReference
    private Customer customer;


    @NotNull(message = "Hamper required - CustomerHamper mapping")
    @ManyToOne
    @JoinColumn(name = "HAMPER_ID")
    private Hamper hamper;

    @Enumerated(EnumType.STRING)
    private PaymentStatusType paymentStatus;

    private boolean paymentComplete;

    private boolean hamperClaimed;

    private String hamperReference;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Hamper getHamper() {
        return hamper;
    }

    public void setHamper(Hamper hamper) {
        this.hamper = hamper;
    }

    public PaymentStatusType getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatusType paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public boolean isPaymentComplete() {
        return paymentComplete;
    }

    public void setPaymentComplete(boolean paymentComplete) {
        this.paymentComplete = paymentComplete;
    }

    public boolean isHamperClaimed() {
        return hamperClaimed;
    }

    public void setHamperClaimed(boolean hamperClaimed) {
        this.hamperClaimed = hamperClaimed;
    }

    public String getHamperReference() {
        return hamperReference;
    }

    public void setHamperReference(String hamperReference) {
        this.hamperReference = hamperReference;
    }
}
