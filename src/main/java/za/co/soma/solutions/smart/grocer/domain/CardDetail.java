package za.co.soma.solutions.smart.grocer.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@Entity
@Table(name = "CARD_DETAIL")
@DynamicUpdate
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, scope = CardDetail.class, property = "id")
public class CardDetail {

    @Transient
    private  static DateFormat cardDateFormat = new SimpleDateFormat("MM/YY");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameOnCard;

    @CreditCardNumber(message = "Invalid card number")
    private String cardNumber;

    @Enumerated(EnumType.STRING)
    private CardType cardType;


    private String expiryDate;

   // @Size(min = 3, max = 3, message = "Invalid CVV")
    private int cvv;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {


        try {
            cardDateFormat.parse(expiryDate);
        } catch (ParseException e) {
           throw  new RuntimeException("Invalid card expiry date format. MM/yy expected");
        }


        this.expiryDate = expiryDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}