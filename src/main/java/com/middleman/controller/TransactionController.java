package com.middleman.controller;

import com.middleman.dto.TransactionRequestDTO;
import com.middleman.entity.Transaction;
import com.middleman.kafka.TransactionProducer;
import com.middleman.service.TransactionService;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionProducer transactionProducer;
    
    @Autowired
    TransactionService transactionService;
    
    Logger logger = LoggerFactory.getLogger(TransactionController.class);
    
    @GetMapping("/{transactionID}")
    public ResponseEntity<String> getTransaction(@PathVariable Long transactionID) {
    	logger.info("getting transaction" + String.valueOf(transactionID));
    	 
    	 Transaction transaction = transactionService.findById(transactionID);
    	return ResponseEntity.status(HttpStatus.ACCEPTED).body(transaction.getStatus());
    }
    

    @PostMapping("/process")
    public ResponseEntity<Map<String, Object>> processTransaction(@RequestBody TransactionRequestDTO transactionDTO) {
    	logger.info("Called process transaction for the idempotency key, " + String.valueOf(transactionDTO.getIdempotencyKey()));
    	String idempotencyKey = transactionDTO.getIdempotencyKey();
        
        // Check if idempotencyKey is null
        if (idempotencyKey == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Idempotency key is required.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        if (transactionService.isDuplicateTransaction(idempotencyKey)) {
        	logger.info("Called duplicate process transaction for the idempotency key, " + String.valueOf(transactionDTO.getIdempotencyKey()));
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Duplicate transaction");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        } else {
        	logger.info("Procecssing transaction for the idempotency key, " + String.valueOf(transactionDTO.getIdempotencyKey()));

        	// Mark as processed to avoid duplication
        	Long transactionId = generateTransactionId();
        	transactionDTO.setTransactionID(transactionId);
        	transactionService.markTransactionAsProcessing(idempotencyKey);

        // Send the transaction request to Kafka
        	transactionProducer.sendTransaction(transactionDTO);
        
        

        // Respond with the transaction ID
        	Map<String, Object> response = new HashMap<>();
        	response.put("transactionId", transactionDTO.getTransactionID());
        	response.put("message", "Transaction request received and being processed");

        	return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        }
    }

    private Long generateTransactionId() {
    	
    	Long uidLong = UUID.randomUUID().getMostSignificantBits();
    	if (uidLong < 0) {
    		uidLong *= -1;
    	}
    	logger.info("Generated New transaction ID, " + String.valueOf(uidLong));
        return  uidLong;//uuid.getMostSigBits()
    }
}

