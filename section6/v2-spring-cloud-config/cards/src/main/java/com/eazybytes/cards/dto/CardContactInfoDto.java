package com.eazybytes.cards.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "cards")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardContactInfoDto {

    String message;
    Map<String, String> contactDetails;
    List<String> onCallSupport;

}
