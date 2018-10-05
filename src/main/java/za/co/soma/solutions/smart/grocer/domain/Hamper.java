package za.co.soma.solutions.smart.grocer.domain;

import za.co.soma.solutions.smart.grocer.domain.validator.Registration;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "HAMPER")
public class Hamper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Null(message = "Vendor must be NULL", groups = Registration.class)
    @ManyToOne
    @JoinColumn(name = "VENDOR_ID", updatable = false, insertable = false)
    private Vendor vendor;

    @OneToMany(mappedBy = "hamper",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerHamper> customerHampers = new ArrayList<>();





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public List<CustomerHamper> getCustomerHampers() {
        return customerHampers;
    }

    public void setCustomerHampers(List<CustomerHamper> customerHampers) {
        this.customerHampers = customerHampers;
    }
}
