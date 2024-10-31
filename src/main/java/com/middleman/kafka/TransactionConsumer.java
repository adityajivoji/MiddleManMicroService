package com.middleman.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import com.middleman.dto.TransactionRequestDTO;
import com.middleman.service.TransactionService;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
@Service
public class TransactionConsumer {
	
    @Autowired
    private TransactionService transactionService;

    @KafkaListener(topics = "transaction_topic", groupId = "transaction-group")
    @Retryable(
        value = Exception.class,
        maxAttempts = 10, // Total attempts, including the first
        backoff = @Backoff(delay = 3000) // Wait time in milliseconds
    )
    public void consumeTransaction(TransactionRequestDTO transactionDTO) {
        // Process the transaction
        transactionService.processTransaction(transactionDTO);
        System.out.println("Consumed transaction: " + transactionDTO.getTransactionID());
    }

    @Recover
    public void recover(Exception e, TransactionRequestDTO transactionDTO) {
        System.out.println("Failed to process transaction after retries: " + transactionDTO.getTransactionID());
        // Handle the failure, e.g., log it or save to a dead letter queue
    }
}

