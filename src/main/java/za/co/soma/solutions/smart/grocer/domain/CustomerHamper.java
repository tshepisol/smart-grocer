package za.co.soma.solutions.smart.grocer.domain;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CUSTOMER_HAMPER")
@DynamicUpdate
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, scope = CustomerHamper.class, property = "id")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class CustomerHamper implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull(message = "Customer required - Customer Hamper mapping")
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
   // @JsonBackReference
    private Customer customer;


    @NotNull(message = "Hamper required - CustomerHamper mapping")
    @ManyToOne
    @JoinColumn(name = "HAMPER_ID")
    private Hamper hamper;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Enumerated(EnumType.STRING)
    private PaymentStatusType paymentStatus;

    private boolean paymentComplete;

    private boolean hamperClaimed;

    @NaturalId
    @Column(nullable = false, updatable = false)
    @NotNull(message = "Hamper Reference cannot be null", groups = Default.class)
    private String hamperReference;

    private String paymentCycle;

    @OneToMany(mappedBy = "customerHamper")
    @JsonManagedReference
    private List<PaymentHistory> paymentHistories = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Hamper getHamper() {
        return hamper;
    }

    public void setHamper(Hamper hamper) {
        this.hamper = hamper;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public PaymentStatusType getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatusType paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public boolean isPaymentComplete() {
        return paymentComplete;
    }

    public void setPaymentComplete(boolean paymentComplete) {
        this.paymentComplete = paymentComplete;
    }

    public boolean isHamperClaimed() {
        return hamperClaimed;
    }

    public void setHamperClaimed(boolean hamperClaimed) {
        this.hamperClaimed = hamperClaimed;
    }

    public String getHamperReference() {
        return hamperReference;
    }

    public void setHamperReference(String hamperReference) {
        this.hamperReference = hamperReference;
    }

    public String getPaymentCycle() {
        return paymentCycle;
    }

    public void setPaymentCycle(String paymentCycle) {
        this.paymentCycle = paymentCycle;
    }

    public List<PaymentHistory> getPaymentHistories() {
        return paymentHistories;
    }

    public void setPaymentHistories(List<PaymentHistory> paymentHistories) {
        this.paymentHistories = paymentHistories;
    }

    @Override
    public String toString() {
        return "CustomerHamper{" +
                "id=" + id +
                ", hamper=" + hamper +
                ", createdAt=" + createdAt +
                ", paymentStatus=" + paymentStatus +
                ", paymentComplete=" + paymentComplete +
                ", hamperClaimed=" + hamperClaimed +
                ", hamperReference='" + hamperReference + '\'' +
                ", paymentCycle='" + paymentCycle + '\'' +
                ", paymentHistories=" + paymentHistories +
                '}';
    }
}
