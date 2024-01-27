package com.peerdeps.peerdepsapi.endpoint.controller;

import com.peerdeps.peerdepsapi.endpoint.security.AuthProvider;
import com.peerdeps.peerdepsapi.model.User;
import com.peerdeps.peerdepsapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class SecurityController {
  private final UserService userService;

  @GetMapping("/signin")
  public User signIn(){
    return AuthProvider.getUser();
  }

  @PostMapping("/signup")
  public User signUp(@RequestBody User user){
    return userService.createUser(user);
  }

}
