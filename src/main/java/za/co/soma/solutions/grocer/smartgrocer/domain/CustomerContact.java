package za.co.soma.solutions.grocer.smartgrocer.domain;

import javax.persistence.*;
import javax.validation.constraints.Digits;

@Entity
@Table(name = "CUSTOMER_CONTACT")
public class CustomerContact {


    @Digits(integer = 10, fraction = 0, message = "Phone number invalid")
    private String number;

    @Enumerated(EnumType.STRING)
    private ContactType contactType;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
