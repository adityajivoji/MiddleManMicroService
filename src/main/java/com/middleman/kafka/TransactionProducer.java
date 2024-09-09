package com.middleman.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.middleman.dto.TransactionRequestDTO;

@Service
public class TransactionProducer {

    private static final String TOPIC = "transaction_topic";

    @Autowired
    private KafkaTemplate<String, TransactionRequestDTO> kafkaTemplate;

    public void sendTransaction(TransactionRequestDTO transactionDTO) {
        kafkaTemplate.send(TOPIC, transactionDTO);
    }
}
