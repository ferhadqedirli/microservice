package com.eazybytes.loans.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "loans")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Loan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long loanId;

    String mobileNumber;

    String loanNumber;

    String loanType;

    double totalLoan;

    double amountPaid;

    double outstandingAmount;

}
