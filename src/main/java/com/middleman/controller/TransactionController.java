package com.middleman.controller;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.middleman.entity.Card;
import com.middleman.entity.Transaction;
import com.middleman.entity.TransactionRequestDTO;
import com.middleman.service.CardService;
import com.middleman.service.TransactionService;
import java.sql.Time;
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    
    @Autowired
    private CardService cardService;
    
    @PostMapping("/process")
    public ResponseEntity<String> processTransaction(@RequestBody TransactionRequestDTO transactionDTO) {
    	Long cardNumber = transactionDTO.getCardNumber();
    	Card card = cardService.findById(cardNumber);
    	Integer cardIndex = card.getCardIndex();
        Long userId = card.getUser().getUserId();


        Time sqlTime = Time.valueOf(LocalTime.now());
        

        Transaction transaction = new Transaction();
        transaction.setCard(card);
        transaction.setTransactionTime(sqlTime);
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setMerchantName(transactionDTO.getMerchantName());
        transaction.setMerchantCity(transactionDTO.getMerchantCity());
        transaction.setMerchantState(transactionDTO.getMerchantState());
        transaction.setMerchantZip(transactionDTO.getMerchantZip());
        transaction.setUseChip(true);  // This would be determined based on actual use case

        transactionService.saveOrUpdateTransaction(transaction);

        return ResponseEntity.status(HttpStatus.CREATED).body("Transaction processed successfully");

    }
    
    @GetMapping("/all")
    public ResponseEntity<String> getallcards() {
    	return ResponseEntity.status(HttpStatus.CREATED).body("HELLO WORLKSDJKS");
    }


}
