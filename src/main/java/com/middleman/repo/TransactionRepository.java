package com.middleman.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.middleman.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {}
