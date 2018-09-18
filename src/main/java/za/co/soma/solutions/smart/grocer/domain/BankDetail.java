package za.co.soma.solutions.smart.grocer.domain;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "BANK_DETAIL")
public class BankDetail {

    @Id
    private Long accountnumber;

    @Enumerated
    private BankType bank;

    private Integer branchCode;

    @Range(min = 1, max = 31, message = "Debit order date invalid")
    private short debitDate;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @OneToMany(mappedBy = "bankDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentHistory> paymentHistoryList = new ArrayList<>();

    public Long getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(Long accountnumber) {
        this.accountnumber = accountnumber;
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

    public short getDebitDate() {
        return debitDate;
    }

    public void setDebitDate(short debitDate) {
        this.debitDate = debitDate;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public List<PaymentHistory> getPaymentHistoryList() {
        return paymentHistoryList;
    }

    public void setPaymentHistoryList(List<PaymentHistory> paymentHistoryList) {
        this.paymentHistoryList = paymentHistoryList;
    }
}
