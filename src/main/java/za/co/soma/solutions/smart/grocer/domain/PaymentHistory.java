package za.co.soma.solutions.smart.grocer.domain;


import javax.persistence.*;

@Entity
@Table(name = "PAYMENT_HISTORY")
public class PaymentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_no_id")
    private BankDetail bankDetail;


    private Short month;

    private short Year;

    private String reference;

    private double amunt;

    @Enumerated(EnumType.STRING)
    private PaymentStatusType paymentStatus;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BankDetail getBankDetail() {
        return bankDetail;
    }

    public void setBankDetail(BankDetail bankDetail) {
        this.bankDetail = bankDetail;
    }

    public Short getMonth() {
        return month;
    }

    public void setMonth(Short month) {
        this.month = month;
    }

    public short getYear() {
        return Year;
    }

    public void setYear(short year) {
        Year = year;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public double getAmunt() {
        return amunt;
    }

    public void setAmunt(double amunt) {
        this.amunt = amunt;
    }

    public PaymentStatusType getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatusType paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
