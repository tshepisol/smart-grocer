package za.co.soma.solutions.grocer.smartgrocer.domain;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "PAYMENT_HISTORY")
public class PaymentHistory {

    private Short month;

    private short Year;

    @Enumerated(EnumType.STRING)
    private PaymentStatusType paymentStatus;
}
