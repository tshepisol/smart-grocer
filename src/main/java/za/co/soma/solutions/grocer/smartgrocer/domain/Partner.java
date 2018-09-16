package za.co.soma.solutions.grocer.smartgrocer.domain;

import javax.persistence.*;


@Entity
@Table(name = "PARTNER")
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PartnerType partnerType;

    private Integer referral;


    //10 - Level 1
    //90 - Level 2
    //590 - Level 3
    //1590 - Level 4
    //4590 - Level 5

}
