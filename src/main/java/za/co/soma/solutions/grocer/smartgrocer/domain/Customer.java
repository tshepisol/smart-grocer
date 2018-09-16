package za.co.soma.solutions.grocer.smartgrocer.domain;


import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String customerNo;

    @NotNull(message = "First name cannot be empty")
    private String firstName;

    @NotNull(message = "Last name cannot be empty")
    private String lastName;

    @Enumerated(EnumType.STRING)
    private GenderType gender;

    @NotNull(message = "ID number cannot be empty")
    @Digits(integer = 13, fraction = 0, message = "ID Number invalid")
    private String IdNumber;

    @Email(message = "Email Address invalid")
    private String emailAdress;

    @OneToOne(optional = true)
    @JoinColumn(name = "CUSTOMER_REFERRAL_ID")
    private Customer customerReferral;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne
    @JoinColumn(name = "PARTNER_ID")
    private  Partner partner;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerContact> customerContacts = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerAddress> customerAddresses = new ArrayList<>();


    @ManyToMany
    @JoinTable(name = "CUSTOMER_HAMPER",
            joinColumns = @JoinColumn(name = "CUSTOMER_ID"),
            inverseJoinColumns = @JoinColumn(name = "HAMPER_ID"))
    private List<Hamper> hampers = new ArrayList<>();


}
