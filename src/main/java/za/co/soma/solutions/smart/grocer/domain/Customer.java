package za.co.soma.solutions.smart.grocer.domain;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.DynamicUpdate;
import za.co.soma.solutions.smart.grocer.domain.validator.Registration;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CUSTOMER")
@DynamicUpdate
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, scope = Customer.class, property = "@id")
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

   /* @Enumerated(EnumType.STRING)
    private GenderType gender;*/

    @NotNull(message = "ID number cannot be empty")
    @Digits(integer = 13, fraction = 0, message = "ID Number invalid")
    private String IdNumber;

    @Email(message = "Email Address invalid")
    private String emailAddress;

    @OneToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_REFERRAL_ID", updatable = false)
   // @JsonManagedReference
    private Customer customerReferral;

    @Valid
    @NotNull(message = "User cannot be NULL", groups = {Registration.class, Default.class})
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private User user;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private  Partner partner;


    @Valid
   // @Size(min = 1, message = "Customer Contact cannot be empty", groups = {Registration.class, Default.class})
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CustomerContact> customerContacts = new ArrayList<>();

    //@Valid
    //@Size(min = 1, message = "Customer Address cannot be empty", groups = {Registration.class, Default.class})
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CustomerAddress> customerAddresses = new ArrayList<>();


    @Valid
    @Size(min = 1, message = "Hamper cannot be empty", groups = {Registration.class, Default.class})
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CustomerHamper> customerHampers = new ArrayList<>();

    @Valid
  //  @NotNull(message = "Bank Details required", groups = {Registration.class, Default.class})
   // @Size(min = 1, message = "BankDetails cannot be empty", groups = {Registration.class, Default.class})
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<BankDetail> bankDetails = new ArrayList<>();



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String idNumber) {
        IdNumber = idNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Customer getCustomerReferral() {
        return customerReferral;
    }

    public void setCustomerReferral(Customer customerReferral) {
        this.customerReferral = customerReferral;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public List<CustomerContact> getCustomerContacts() {
        return customerContacts;
    }

    public void setCustomerContacts(List<CustomerContact> customerContacts) {
        this.customerContacts = customerContacts;
    }

    public List<CustomerAddress> getCustomerAddresses() {
        return customerAddresses;
    }

    public void setCustomerAddresses(List<CustomerAddress> customerAddresses) {
        this.customerAddresses = customerAddresses;
    }

    public List<CustomerHamper> getCustomerHampers() {
        return customerHampers;
    }

    public void setCustomerHampers(List<CustomerHamper> customerHampers) {
        this.customerHampers = customerHampers;
    }

    public List<BankDetail> getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(List<BankDetail> bankDetails) {
        this.bankDetails = bankDetails;
    }
}
