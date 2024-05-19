package com.eazybytes.cards.service;

import com.eazybytes.cards.dto.CardContactInfoDto;
import com.eazybytes.cards.dto.CardDto;
import com.eazybytes.cards.dto.ResponseDto;
import org.springframework.http.ResponseEntity;

public interface CardService {

    /**
     *
     * @param mobileNumber - Mobile Number of the Customer
     */
    ResponseEntity<ResponseDto> createCard(String mobileNumber);

    /**
     *
     * @param mobileNumber - Input mobile Number
     * @return Card Details based on a given mobileNumber
     */
    ResponseEntity<CardDto> fetchCard(String mobileNumber);

    /**
     *
     * @param cardsDto - CardsDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    ResponseEntity<ResponseDto> updateCard(CardDto cardsDto);

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of card details is successful or not
     */
    ResponseEntity<ResponseDto> deleteCard(String mobileNumber);

    ResponseEntity<String> getBuildInfo();

    ResponseEntity<String> getJavaVersion();

    ResponseEntity<CardContactInfoDto> getContactInfo();

}
