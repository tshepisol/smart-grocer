package za.co.soma.solutions.smart.grocer.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import za.co.soma.solutions.smart.grocer.domain.validator.Registration;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "HAMPER")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, scope = Hamper.class, property = "id")
public class Hamper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String productCode;

    private String name;

    @Enumerated(EnumType.STRING)
    private HamperCategoryType category;

    private String brand;

    private Integer quantity;

    private Integer unitMeasure;

    private BigDecimal price;

    @Null(message = "Vendor must be NULL", groups = Registration.class)
    @ManyToOne
    @JoinColumn(name = "VENDOR_ID", updatable = false, insertable = false)
    private Vendor vendor;

/*    @OneToMany(mappedBy = "hamper",  cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonManagedReference
    private List<CustomerHamper> customerHampers = new ArrayList<>();*/


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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public HamperCategoryType getCategory() {
        return category;
    }

    public void setCategory(HamperCategoryType category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getUnitMeasure() {
        return unitMeasure;
    }

    public void setUnitMeasure(Integer unitMeasure) {
        this.unitMeasure = unitMeasure;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    /*public List<CustomerHamper> getCustomerHampers() {
        return customerHampers;
    }

    public void setCustomerHampers(List<CustomerHamper> customerHampers) {
        this.customerHampers = customerHampers;
    }*/
}
