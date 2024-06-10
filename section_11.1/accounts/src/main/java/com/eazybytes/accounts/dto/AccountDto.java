package com.eazybytes.accounts.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record AccountDto(

        @Schema(
                description = "Account Number of Eazy Bank account", example = "3454433243"
        )
        @NotNull(message = "AccountNumber can not be a null or empty")
        Long accountNumber,

        @Schema(
                description = "Account type of Eazy Bank account", example = "Savings"
        )
        @NotEmpty(message = "Account type can not be null or empty")
        String AccountType,

        @Schema(
                description = "Eazy Bank branch address", example = "123 NewYork"
        )
        @NotEmpty(message = "Branch address can not be null or empty")
        String branchAddress

) {
}
