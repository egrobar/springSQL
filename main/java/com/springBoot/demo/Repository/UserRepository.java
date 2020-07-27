package com.springBoot.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springBoot.demo.Entity.*;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);


  // @Query("SELECT * FROM users u where u.username = :username") 
  //   String findUserbyUsername(@Param("username") String username);
}