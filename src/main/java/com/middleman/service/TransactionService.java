package com.middleman.service;

import java.sql.Time;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.middleman.dto.TransactionRequestDTO;
import com.middleman.entity.Card;
import com.middleman.entity.Transaction;
import com.middleman.repo.CardRepository;
import com.middleman.repo.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    
    @Autowired
    private CardRepository cardRepository;
    
    public Transaction findById(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found with ID: " + transactionId));
        
        return transaction; // Replace with actual status retrieval
    }
    public String getStatus(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found with ID: " + transactionId));
        
        return transaction.getStatus(); // Replace with actual status retrieval
    }

    public void processTransaction(TransactionRequestDTO transactionDTO) {
//        Transaction transaction = transactionRepository.findById(transactionId)
//                .orElseThrow(() -> new RuntimeException("Transaction not found with ID: " + transactionId));
    	Long cardNumber = transactionDTO.getCardNumber();
        Card card = cardRepository.findById(cardNumber)
                .orElseThrow(() -> new RuntimeException("Card not found with ID: " + cardNumber));

        Time sqlTime = Time.valueOf(LocalTime.now());

        Transaction transaction = new Transaction();
        transaction.setTransactionId(transactionDTO.getTransactionID());
        transaction.setCard(card);
        transaction.setTransactionTime(sqlTime);
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setMerchantName(transactionDTO.getMerchantName());
        transaction.setMerchantCity(transactionDTO.getMerchantCity());
        transaction.setMerchantState(transactionDTO.getMerchantState());
        transaction.setMerchantZip(transactionDTO.getMerchantZip());
        transaction.setUseChip(true);  // Adjust based on actual logic
        transaction.setStatus("Authorised");
        // Set other fields if needed
        
        transactionRepository.save(transaction);
    }
}
