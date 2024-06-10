package com.eazybytes.loans.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record LoanDto(

        @NotEmpty(message = "Mobile Number can not be a null or empty")
        @Pattern(regexp = "^994\\d{9}$", message = "Mobile number must be 12 digits and starts with '994'")
        @Schema(
                description = "Mobile Number of Customer", example = "4365327698"
        )
        String mobileNumber,

        @NotEmpty(message = "Loan Number can not be a null or empty")
        @Pattern(regexp = "(^$|[0-9]{12})", message = "LoanNumber must be 12 digits")
        @Schema(
                description = "Loan Number of the customer", example = "548732457654"
        )
        String loanNumber,

        @NotEmpty(message = "LoanType can not be a null or empty")
        @Schema(
                description = "Type of the loan", example = "Home Loan"
        ) String loanType,

        @Positive(message = "Total loan amount should be greater than zero")
        @Schema(
                description = "Total loan amount", example = "100000"
        )
        double totalLoan,

        @PositiveOrZero(message = "Total loan amount paid should be equal or greater than zero")
        @Schema(
                description = "Total loan amount paid", example = "1000"
        )
        double amountPaid,

        @PositiveOrZero(message = "Total outstanding amount should be equal or greater than zero")
        @Schema(
                description = "Total outstanding amount against a loan", example = "99000"
        )
        double outstandingAmount

) {
}
