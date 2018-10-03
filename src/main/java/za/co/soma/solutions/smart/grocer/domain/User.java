package za.co.soma.solutions.smart.grocer.domain;


import za.co.soma.solutions.smart.grocer.domain.validator.Registration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
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

    @NotNull(message = "Username name Required", groups = {Registration.class, Default.class})
    private String username;

    @NotNull(message = "Password Required", groups = {Registration.class, Default.class})
    private String password;

    private int retryCount = 0;

    private boolean locked = false;

    @OneToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;


    @Size(message = "Role must be empty", groups = Registration.class)
    @ManyToMany()
    @JoinTable(name = "USER_ROLE",
        joinColumns = @JoinColumn(name = "USER_ID"),
        inverseJoinColumns = @JoinColumn(name = "ROLE_ID", updatable = false, insertable = false))
    private List<Role> roles = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
