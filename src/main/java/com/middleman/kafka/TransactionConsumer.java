package com.middleman.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.middleman.dto.TransactionRequestDTO;
import com.middleman.service.TransactionService;

@Service
public class TransactionConsumer {
	
	@Autowired
	private TransactionService transactionService;

    @KafkaListener(topics = "transaction_topic", groupId = "transaction-group")
    public void consumeTransaction(TransactionRequestDTO transactionDTO) {
        // Process the transaction
    	transactionService.processTransaction(transactionDTO);
        System.out.println("Consumed transaction: " + transactionDTO.getTransactionID());
        // Add your processing logic here
    }
}

