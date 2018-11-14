package za.co.soma.solutions.smart.grocer.domain;

import javax.persistence.*;

@Entity
@Table(name = "HAMPER_NO")
public class HamperNo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int hamperNumber;

    @PrePersist
    public void persist(){
        hamperNumber =1;
    }


    public void increment(){
        hamperNumber++;
    }

    public int getHamperNumber() {
        return hamperNumber;
    }


}
