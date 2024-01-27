package com.peerdeps.peerdepsapi.service;

import com.peerdeps.peerdepsapi.model.exception.NotFoundException;
import com.peerdeps.peerdepsapi.repository.UserRepository;
import com.peerdeps.peerdepsapi.model.User;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
  private final UserRepository repository;

  public User findById(String userId){
    return repository.findById(userId)
        .orElseThrow(()->new NotFoundException("User."+userId+" is not found"));
  }

  public User getUserByFirebaseIdAndEmail(String uid, String email) {
    return null;
  }

  public List<User> findAll(Integer page, Integer pageSize){
    int pageValue = page == null ? 0 : page - 1;
    int sizeValue = pageSize == null ? 10 : pageSize;
    Pageable pageable = PageRequest.of(pageValue, sizeValue);
    return repository.findAll(pageable).stream().toList();
  }

}
