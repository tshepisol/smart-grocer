package za.co.soma.solutions.grocer.smartgrocer.domain;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "USER")
public class User {


    @PrePersist
    public void setCreatedDate(){
        createAt = new Date();
    }

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private int retryCount;
    private boolean locked;



    @OneToOne(mappedBy = "user")
    private Customer customer;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    @ManyToMany
    @JoinTable(name = "USER_ROLE",
        joinColumns = @JoinColumn(name = "USER_ID"),
        inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private List<Role> roles = new ArrayList<>();
}
