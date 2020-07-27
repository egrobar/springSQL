package com.springBoot.demo.service;

import com.springBoot.demo.Entity.User;
import com.springBoot.demo.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  private UserRepository repository;
  
  public User saveUser(User user) {
    return repository.save(user);
  }


  public User getUserByUsername(String username){
    return repository.findByUsername(username);
  }

  
}