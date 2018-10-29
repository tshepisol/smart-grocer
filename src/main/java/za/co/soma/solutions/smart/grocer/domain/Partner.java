package za.co.soma.solutions.smart.grocer.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "PARTNER")
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PartnerType partnerType;

    private Integer referral;

    @OneToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

/*
    LEVEL_1, //10
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PartnerType getPartnerType() {
        return partnerType;
    }

    public void setPartnerType(PartnerType partnerType) {
        this.partnerType = partnerType;
    }

    public Integer getReferral() {
        return referral;
    }

    public void setReferral(Integer referral) {
        this.referral = referral;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
