package com.springBoot.demo.Controller;

import com.springBoot.demo.Entity.User;
import com.springBoot.demo.service.UserService;
import com.springBoot.models.AuthenticationRequest;
import com.springBoot.models.AuthenticationResponse;
import com.springBoot.demo.utils.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.springBoot.demo.Config.*;

@RestController
public class UserController {
  @Autowired
  private UserService service;
  // @Autowired
  // private AuthenticationManager authenticationManager;
  @Autowired
  JwtUtil jwtTokenUtil;

  @PostMapping("/addUser")
  public User addUser(@RequestBody User user) {
    return service.saveUser(user);
  }
  @GetMapping("/user/{username}")
  public User getUserByUsername(@PathVariable String username) {
    return service.getUserByUsername(username);
  }
  @PostMapping("/authenticate")
  public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
    // try {
    //   authenticationManager.authenticate(
    //   new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
    // );
    // } catch (BadCredentialsException e) {

    //   throw new Exception("Incorrect Username or Password", e);
    // }
    System.out.println("in authenticate");

    final User user = service
      .getUserByUsername(authenticationRequest.getUsername());
      System.out.println(user.getPassword());
      
    
    if (BCrypt.checkpw(authenticationRequest.getPassword(), user.getPassword())) {
      final String jwt = jwtTokenUtil.generateToken(user);
      System.out.println("jwtToken: " + jwt);
      return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
    return ResponseEntity.ok("Password Incorrect");
  }
}