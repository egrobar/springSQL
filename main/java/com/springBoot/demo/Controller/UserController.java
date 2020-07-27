package com.springBoot.demo.Controller;

import com.springBoot.demo.Entity.User;
import com.springBoot.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  @Autowired
  private UserService service;

  @PostMapping("/addUser")
  public User addUser(@RequestBody User user) {
    return service.saveUser(user);
  }
  @GetMapping("/user/{username}")
  public User getUserByUsername(@PathVariable String username) {
    return service.getUserByUsername(username);
  }
}