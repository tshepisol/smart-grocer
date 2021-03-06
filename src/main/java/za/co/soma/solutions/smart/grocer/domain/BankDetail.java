package za.co.soma.solutions.smart.grocer.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Range;
import za.co.soma.solutions.smart.grocer.domain.validator.Registration;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "BANK_DETAIL")
@DynamicUpdate
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, scope = BankDetail.class, property = "accountNumber")
public class BankDetail {

    @Id
    @NotNull(message = "Account NUmber cannot be empty", groups = {Registration.class, Default.class})
    private Long accountNumber;

    @Enumerated
    @NotNull(message = "Bank cannot be empty", groups = {Registration.class, Default.class})
    private BankType bank;

    @NotNull(message = "Bank cannot be empty", groups = {Registration.class, Default.class})
    private Integer branchCode;

    @NotNull(message = "Date cannot be empty", groups = {Registration.class, Default.class})
    @Range(min = 1, max = 31, message = "Debit order date invalid")
    private Integer debitDate;

    @NotNull(message = "Account Type cannot be empty", groups = {Registration.class, Default.class})
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;

    @Valid
   // @Size(max = 0, message = "Payment History must be EMPTY", groups = Registration.class)
    @OneToMany(mappedBy = "bankDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentHistory> paymentHistoryList = new ArrayList<>();

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BankType getBank() {
        return bank;
    }

    public void setBank(BankType bank) {
        this.bank = bank;
    }

    public Integer getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(Integer branchCode) {
        this.branchCode = branchCode;
    }

    public Integer getDebitDate() {
        return debitDate;
    }

    public void setDebitDate(Integer debitDate) {
        this.debitDate = debitDate;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<PaymentHistory> getPaymentHistoryList() {
        return paymentHistoryList;
    }

    public void setPaymentHistoryList(List<PaymentHistory> paymentHistoryList) {
        this.paymentHistoryList = paymentHistoryList;
    }
}
