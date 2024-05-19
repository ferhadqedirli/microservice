package com.eazybytes.loans.service;

import com.eazybytes.loans.dto.LoanContactInfoDto;
import com.eazybytes.loans.dto.LoanDto;
import com.eazybytes.loans.dto.ResponseDto;
import org.springframework.http.ResponseEntity;

public interface LoanService {

    /**
     *
     * @param mobileNumber - Mobile Number of the Customer
     */
    ResponseEntity<ResponseDto> createLoan(String mobileNumber);

    /**
     *
     * @param mobileNumber - Input mobile Number
     * @return Loan Details based on a given mobileNumber
     */
    ResponseEntity<LoanDto> fetchLoan(String mobileNumber);

    /**
     *
     * @param loansDto - LoansDto Object
     * @return ResponseDto indicating if the update of card details is successful or not
     */
    ResponseEntity<ResponseDto> updateLoan(LoanDto loansDto);

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return ResponseDto indicating if the delete of loan details is successful or not
     */
    ResponseEntity<ResponseDto> deleteLoan(String mobileNumber);

    ResponseEntity<String> getBuildInfo();

    ResponseEntity<String> getJavaVersion();

    ResponseEntity<LoanContactInfoDto> getContactInfo();

}
