package com.middleman.service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.middleman.dto.TransactionRequestDTO;
import com.middleman.entity.Card;
import com.middleman.entity.Transaction;
import com.middleman.repo.CardRepository;
import com.middleman.repo.TransactionRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

import org.springframework.http.HttpMethod;
@Service
public class TransactionService {

    private static final String AMOUNT_VALIDATION_URL_1 = "http://127.0.0.1:5000/validate-amount";
    
    private static final String AMOUNT_VALIDATION_URL_2 = "http://127.0.0.1:8000/validate-amount";

	@Autowired
    private TransactionRepository transactionRepository;
    
    @Autowired
    private CardRepository cardRepository;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    private static final long CACHE_EXPIRY_TIME = 1; // in hours, set as needed

    public boolean isDuplicateTransaction(String idempotencyKey) {
        return redisTemplate.hasKey(idempotencyKey);
    }
    public void markTransactionAsProcessing(String idempotencyKey) {
        redisTemplate.opsForValue().set(idempotencyKey, "PROCESSING", CACHE_EXPIRY_TIME, TimeUnit.HOURS);
    }

    public void markTransactionAsProcessed(String idempotencyKey) {
        redisTemplate.opsForValue().set(idempotencyKey, "PROCESSED", CACHE_EXPIRY_TIME, TimeUnit.HOURS);
    }
    
    @Cacheable(value = "transactions", key = "#transactionId")
    public Transaction findById(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found with ID: " + transactionId));
        
        return transaction; // Replace with actual status retrieval
    }
    
    @Cacheable(value = "transactionStatus", key = "#transactionId")
    public String getStatus(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found with ID: " + transactionId));
        
        return transaction.getStatus(); // Replace with actual status retrieval
    }

    public void processTransaction(TransactionRequestDTO transactionDTO) {

    	Long cardNumber = transactionDTO.getCardNumber();
        Card card = cardRepository.findById(cardNumber)
                .orElseThrow(() -> new RuntimeException("Card not found with ID: " + cardNumber));

        Map<String, Object> requestPayload = new HashMap<>();
        requestPayload.put("amount", transactionDTO.getAmount());
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestPayload);

        // Call the first validation service with circuit breaker and retry
        Map<String, Object> val_amount_1 = validateAmountService1(requestEntity);
        
        // Call the second validation service with circuit breaker and retry
        Map<String, Object> val_amount_2 = validateAmountService2(requestEntity);

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
        transaction.setUseChip(true);

        // Check responses from both validation services
        if ((val_amount_1 != null && Boolean.TRUE.equals(val_amount_1.get("valid"))) &&
            (val_amount_2 != null && Boolean.TRUE.equals(val_amount_2.get("valid")))) {
            transaction.setStatus("Authorised");
        } else {
            transaction.setStatus("UnAuthorised, Amount exceeded!!");
        }

        transactionRepository.save(transaction);
    }

    // Circuit breaker and retry for the first validation service
    @CircuitBreaker(name = "validateAmountService1", fallbackMethod = "fallbackValidateAmountService")
    @Retry(name = "validateAmountService1")
    public Map<String, Object> validateAmountService1(HttpEntity<Map<String, Object>> requestEntity) {
        ResponseEntity<Map<String, Object>> responseEntity = restTemplate.exchange(
                AMOUNT_VALIDATION_URL_1,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {}
        );
        return responseEntity.getBody();
    }

    // Circuit breaker and retry for the second validation service
    @CircuitBreaker(name = "validateAmountService2", fallbackMethod = "fallbackValidateAmountService")
    @Retry(name = "validateAmountService2")
    public Map<String, Object> validateAmountService2(HttpEntity<Map<String, Object>> requestEntity) {
        ResponseEntity<Map<String, Object>> responseEntity = restTemplate.exchange(
                AMOUNT_VALIDATION_URL_2,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<>() {}
        );
        return responseEntity.getBody();
    }

    // Fallback method for validation services
    public Map<String, Object> fallbackValidateAmountService(HttpEntity<Map<String, Object>> requestEntity, Throwable t) {
        Map<String, Object> fallbackResponse = new HashMap<>();
        fallbackResponse.put("valid", false);  // Default to invalid in fallback
        return fallbackResponse;
    }
}
