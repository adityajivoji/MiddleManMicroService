package com.middleman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.middleman.entity.Card;
import com.middleman.repo.CardRepository;

@Service
public class CardService {

    @Autowired
    public CardRepository cardRepository;

    // Method to find a card by its ID
    @Cacheable(value = "cards", key = "#id")
    public Card findById(Long id) {
        return cardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card not found with ID: " + id));
    }

    // Method to get all cards
    @Cacheable(value = "allCards")
    public List<Card> getAllCards() {
        List<Card> cards = cardRepository.findAll();
        System.out.println("Fetched cards: " + cards);
        return cards;
    }
}
