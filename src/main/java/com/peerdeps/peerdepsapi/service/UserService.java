package com.peerdeps.peerdepsapi.service;

import com.peerdeps.peerdepsapi.model.exception.NotFoundException;
import com.peerdeps.peerdepsapi.repository.UserRepository;
import com.peerdeps.peerdepsapi.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
  private final UserRepository repository;

  public User findById(String userId){
    return repository.findById(userId)
        .orElseThrow(()->new NotFoundException("User."+userId+" is not found"));
  }

}
