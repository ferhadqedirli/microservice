package com.eazybytes.accounts.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "accounts")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountContactInfoDto {

    String message;
    Map<String, String> contactDetails;
    List<String> onCallSupport;

}
