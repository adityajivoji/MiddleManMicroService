package com.middleman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.middleman.entity.Card;
//import com.middleman.entity.CardId;
import com.middleman.entity.Transaction;
//import com.middleman.repo.CardRepository;
import com.middleman.repo.TransactionRepository;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    
    
//    @Autowired
//    private CardRepository cardRepository;

    public Transaction authorizeTransaction(Transaction transaction) {
//        CardId cardId = new CardId(transaction.getCard().getUser().getUserId(), transaction.getCard().getCardId().getCardIndex());
//        Card card = cardRepository.findById(cardId)
//                          .orElseThrow(() -> new RuntimeException("Card not found"));

        // Add business logic for transaction validation and processing
        // ...
        
        return transactionRepository.save(transaction);
    }
    
    public Transaction saveOrUpdateTransaction(Transaction transaction) {
    	return transactionRepository.save(transaction);
    }


}
