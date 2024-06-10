package com.eazybytes.accounts.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "Response",
        description = "Schema to hold successful response information"
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResponseDto(

        @Schema(
                description = "Status code in the response"
        )
        String statusCode,

        @Schema(
                description = "Status message in the response"
        )
        String statusMessage

) {
}
