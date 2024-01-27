package com.peerdeps.peerdepsapi.endpoint.controller;

import com.peerdeps.peerdepsapi.model.User;
import com.peerdeps.peerdepsapi.service.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class UserController {
  private final UserService service;

  @GetMapping(value = "/users/{id}")
  public User getUser(@PathVariable("id") String id){
    return service.findById(id);
  }

  @GetMapping(value = "/users")
  public List<User> findAll(
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "pageSize", required = false) Integer pageSize
  ){
    return service.findAll(page, pageSize);
  }

}
