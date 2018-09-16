package za.co.soma.solutions.grocer.smartgrocer.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CUSTOMER_ADDRESS")
public class CustomerAddress {

    @NotNull(message = "Number field cannot be empty")
    private String number;

    private String streetName;

    private String town;

    @Enumerated(EnumType.STRING)
    private ProvinceType province;

    private Integer postalCode;

    @Enumerated(EnumType.STRING)
    private  AddressType addressType;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
