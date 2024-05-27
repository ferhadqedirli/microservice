package com.eazybytes.accounts.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public record CustomerDto(

        @Schema(
                description = "Name of the customer", example = "Eazy Bytes"
        )
        @NotEmpty(message = "Name can not be null or empty")
        @Size(min = 5, max = 30, message = "The length of the customer name should be between {min} and {max}")
        String name,

        @Schema(
                description = "Email address of the customer", example = "tutor@eazybytes.com"
        )
        @NotEmpty(message = "Email address can not be null or empty")
        @Email(message = "Email address should be valid value")
        String email,

        @Schema(
                description = "Mobile Number of the customer", example = "994558461440"
        )
        @Pattern(regexp = "^994\\d{9}$", message = "Mobile number must be 12 digits and starts with '994'")
        String mobileNumber,

        @Schema(
                description = "Account details of the Customer"
        )
        @Valid
        AccountDto account

) {
}
