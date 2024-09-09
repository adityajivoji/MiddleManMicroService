package com.middleman.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.middleman.entity.Card;




public interface CardRepository extends JpaRepository<Card, Long> {}
