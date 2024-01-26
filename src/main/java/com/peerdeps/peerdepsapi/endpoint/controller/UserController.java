package com.peerdeps.peerdepsapi.endpoint.controller;

import com.peerdeps.peerdepsapi.model.User;
import com.peerdeps.peerdepsapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {
  private final UserService service;

  @GetMapping(value = "/users/{id}")
  public User getUser(@PathVariable("id") String id){
    return service.findById(id);
  }

}
