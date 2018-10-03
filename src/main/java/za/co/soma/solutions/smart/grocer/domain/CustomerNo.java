package za.co.soma.solutions.smart.grocer.domain;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOMER_NO")
public class CustomerNo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int customerNumber;

    @PrePersist
    public void persist(){
        customerNumber=1;
    }


    public void increment(){
        customerNumber++;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }


}
