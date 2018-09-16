package za.co.soma.solutions.grocer.smartgrocer.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "HAMPER")
public class Hamper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "VENDOR_ID")
    private Vendor vendor;

    @ManyToMany(mappedBy = "hampers")
    private List<Customer> customers = new ArrayList<>();

}
