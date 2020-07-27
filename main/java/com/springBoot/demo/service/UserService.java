package com.springBoot.demo.service;

import com.springBoot.demo.Entity.User;
import com.springBoot.demo.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Service
public class UserService implements UserDetailsService {
  @Autowired
  private UserRepository repository;

  public User saveUser(User user) {
    String unencryptedPW = user.getPassword();
    String encryptedPW = BCrypt.hashpw(unencryptedPW, BCrypt.gensalt());
    user.setPassword(encryptedPW);
    return repository.save(user);
  }

  public User getUserByUsername(String username) {
    System.out.println("in get userbyUsername");
    return repository.findByUsername(username);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // TODO Auto-generated method stub
    return repository.findByUsername(username);
  }

  
}