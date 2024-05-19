package com.eazybytes.cards.mapper;

import com.eazybytes.cards.dto.CardDto;
import com.eazybytes.cards.entity.Card;

public class CardMapper {

    public static CardDto mapToCardsDto(Card card) {
        return new CardDto(
                card.getMobileNumber(),
                card.getCardNumber(),
                card.getCardType(),
                card.getTotalLimit(),
                card.getAmountUsed(),
                card.getAvailableAmount()
        );
    }

    public static Card mapToCards(CardDto cardDto, Card card) {
        card.setCardNumber(cardDto.cardNumber());
        card.setCardType(cardDto.cardType());
        card.setMobileNumber(cardDto.mobileNumber());
        card.setTotalLimit(cardDto.totalLimit());
        card.setAvailableAmount(cardDto.availableAmount());
        card.setAmountUsed(cardDto.amountUsed());
        return card;
    }

}
