package com.eazybytes.accounts.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "ACCOUNTS")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account extends BaseEntity {

    @Id
    @Column(updatable = false)
    Long accountNumber;

    Long customerId;

    String accountType;

    String branchAddress;


}
