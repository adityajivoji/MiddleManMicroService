package com.middleman.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.middleman.entity.User;
public interface UserRepository extends JpaRepository<User, Long> {}
