package za.co.soma.solutions.grocer.smartgrocer.domain;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;


@Entity
@Table(name = "BANK_DETAIL")
public class BankDetail {

    private String bankName;

    private Long accountnumber;

    private Integer branchCode;

    @Range(min = 1, max = 31, message = "Debit order date invalid")
    private short debitDate;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;
}
