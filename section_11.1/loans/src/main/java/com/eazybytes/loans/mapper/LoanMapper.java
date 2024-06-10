package com.eazybytes.loans.mapper;

import com.eazybytes.loans.dto.LoanDto;
import com.eazybytes.loans.entity.Loan;

public class LoanMapper {

    public static LoanDto mapToLoansDto(Loan loan) {
        return new LoanDto(
                loan.getMobileNumber(),
                loan.getLoanNumber(),
                loan.getLoanType(),
                loan.getTotalLoan(),
                loan.getAmountPaid(),
                loan.getOutstandingAmount()
        );
    }

    public static Loan mapToLoans(LoanDto loanDto, Loan loans) {
        loans.setLoanNumber(loanDto.loanNumber());
        loans.setLoanType(loanDto.loanType());
        loans.setMobileNumber(loanDto.mobileNumber());
        loans.setTotalLoan(loanDto.totalLoan());
        loans.setAmountPaid(loanDto.amountPaid());
        loans.setOutstandingAmount(loanDto.outstandingAmount());
        return loans;
    }

}
